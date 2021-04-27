package com.capsilon.automation.aus.ui.base;

import com.capsilon.automation.aus.dto.DuReport;
import com.capsilon.automation.aus.dto.FolderWithReports;
import com.capsilon.automation.aus.rest.FoldersService;
import com.capsilon.automation.aus.rest.ReportType;
import com.capsilon.automation.aus.rest.uploadDocuments.UploadSmartDocument;
import com.capsilon.automation.aus.ui.AusAnalyzerPage;
import com.capsilon.automation.aus.utils.UploadUtils;
import com.capsilon.automation.bam.pipelineapi.LoanFolderRestApi;
import com.capsilon.automation.bam.rest.admin.BizAppDataProvider;
import com.capsilon.automation.bam.ui.pipeline.pages.AngularLoginPage;
import com.capsilon.automation.bam.ui.pipeline.pages.PipelinePage;
import com.capsilon.automation.bam.ui.tpo.TpoPage;
import com.capsilon.test.commons.selenide.SelenideJUnit5TestBase;
import com.capsilon.test.run.confiuration.BizappsConfig;
import com.capsilon.test.ui.Wait;
import com.capsilon.test.ui.components.Browser;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.capsilon.automation.aus.utils.ReportUtils.getLatestReport;
import static com.capsilon.automation.bam.ui.container.ContainerPage.leftMenuView;
import static com.capsilon.automation.bam.ui.tpo.TpoPage.logout;
import static com.codeborne.selenide.Selenide.*;

public abstract class TestBase extends SelenideJUnit5TestBase {

    private static final String AUS_ANALYZER_WORKSPACE = "AUS Analyzer Workspace";

    protected static final boolean createNewFolder = true;
    protected static final boolean createNewReport = true;

    protected static final PipelinePage pipelinePage = new PipelinePage();
    protected static final AusAnalyzerPage ausPage = new AusAnalyzerPage();

    protected static String folderId;

    protected static FolderWithReports folderWithReports;
    protected static DuReport report;

    protected static final FoldersService folderService = new FoldersService();
    protected static final UploadSmartDocument uploadSmartDocument = new UploadSmartDocument();

    protected static final int POPOVER_TIME = 2000;
    protected static final int REVIEW_TIMEOUT = 15000;
    protected static final int DOCUMENTS_UPLOAD_TIMEOUT = 300000;

    static {
        Configuration.timeout = 60000;
        Configuration.startMaximized = true;
    }

    @SneakyThrows
    @BeforeAll
    static void loginAndOpenAusAnalyzerApp() {
        folderService.prepareData(BizAppDataProvider.USERNAME, BizAppDataProvider.PASSWORD);

        if (createNewFolder) {
            folderId = folderService.createNewFolder();
        } else if (folderId == null) {
            LoanFolderRestApi folderRestApi = new LoanFolderRestApi();
            folderId = folderRestApi
                .paginationForFolders(0, 10, null)
                .getLoanFolders()
                .stream()
                .findFirst()
                .orElseThrow(() -> new AssertionError("Loans not found!"))
                .getLoanApplicationFolderId();
        }

        if (createNewReport) {
            uploadDuReport();
        }

        AngularLoginPage.openAndLogin(BizappsConfig.getUnderwriterUser(), BizappsConfig.getUnderwriterUserPassword());
        Wait.waitUntilOneElementVisible(BizappsConfig.getMaxLoginTime(), TpoPage.pipelineView.tableHeader.COMPONENT_CONTAINER);

        open(BizappsConfig.getBaseUrl() + "/bac/?folder=" + folderId);

        pipelinePage.changeWorkspace(AUS_ANALYZER_WORKSPACE);

        leftMenuView.openBizapAndWaitForLoad(
            leftMenuView.leftMenu.ausAnalyzer,
            ausPage.getRecommendation(), ausPage.getSummary()
        );

        folderWithReports = folderService.getFolderWithReports(folderId);
        report = getLatestReport(folderWithReports, ReportType.DU);
    }

    @SneakyThrows
    @AfterAll
    public static void finish() {
        folderService.deleteSession();
        switchTo().defaultContent();
        logout();
    }

    public static void reloadAUSApp() {
        Browser.goToParentFrame();
        leftMenuView.waitTillAnyBizapSelected();
        leftMenuView.leftMenu.ausAnalyzer.select();
        sleep(1000);
        Wait.waitUntilElementExistWithoutThrowingException(Selenide.$("#iframe-wrapper"));
        Browser.goToFrameByNameOrId("iframe-wrapper");
        Wait.waitUntilOneElementVisible(BizappsConfig.getMaxBizappLoadTime(), ausPage.getRecommendation());
    }

    public static void uploadDuReport() {
        uploadSmartDocument.uploadSmartXml(
            folderId, UploadUtils.getFilePath("du-findings.xml"), "XML");
        sleep(6000); // TODO notification waiter
    }
}
