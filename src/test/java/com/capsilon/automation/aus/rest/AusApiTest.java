package com.capsilon.automation.aus.rest;

import com.capsilon.automation.aus.dto.Document;
import com.capsilon.automation.aus.dto.DuReport;
import com.capsilon.automation.aus.dto.Finding;
import com.capsilon.automation.aus.dto.FindingsGroup;
import com.capsilon.automation.aus.rest.base.RestTestBase;
import com.capsilon.automation.aus.rest.uploadDocuments.UploadSmartDocument;
import com.capsilon.automation.aus.utils.CSVUtils;
import com.capsilon.automation.aus.utils.UploadUtils;
import com.capsilon.automation.bam.rest.admin.BizAppDataProvider;
import com.capsilon.test.run.confiuration.BizappsConfig;
import com.capsilon.test.ui.Retry;
import com.google.common.collect.ArrayListMultimap;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVRecord;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.capsilon.automation.aus.rest.ReportType.DU;
import static com.capsilon.automation.aus.utils.CSVUtils.getDuMappingRecords;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class AusApiTest extends RestTestBase {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AusApiTest.class);

    private static final String[] DU_CSV_HEADERS = {"id", "content", "visible"};
    private static final String DU_CSV_FILE = "init_du_msg_visibility.csv";

    private static final FoldersService folderService = new FoldersService();
    private static final ReportsService reportService = new ReportsService();
    private static final UploadSmartDocument uploadSmartDocument = new UploadSmartDocument();

    private static final int DOCUMENTS_UPLOAD_TIMEOUT = 180000;
    private static final int DOCUMENT_REVIEW_TIMEOUT = 15000;
    private static final int POLLING_INTERVAL = 2000;

    private static String folderId;
    private static String reportId;

    private static boolean createNewFolder = true;
    private static boolean createNewReport = true;

    @SneakyThrows
    @BeforeAll
    public static void setUp() {
        folderService.prepareData(BizAppDataProvider.USERNAME, BizAppDataProvider.PASSWORD);

        if (createNewFolder) {
            folderId = folderService.createNewFolder();
        } else {
            switch (BizappsConfig.getEnvId()) {
                case "int":
                    folderId = "29502";
                    break;
                case "qa-mdms":
                    folderId = "706";
                    break;
                case "dev-mdms":
                    folderId = "529";
                    break;
                case "precrt-acc5":
                    folderId = "9484";
                    break;
                case "crt":
                    folderId = "13102";
            }
        }

        if (createNewReport) {
            int initialDocuments;
            if (createNewFolder) {
                initialDocuments = 0;
                ClassLoader classLoader = AusApiTest.class.getClassLoader();
                uploadSmartDocument.uploadSmartXml(
                    folderId, Objects.requireNonNull(classLoader.getResource("du-findings.xml")).getPath(), "XML");
                sleep(15000);
                uploadSmartDocument.uploadDocument(
                    folderId, Objects.requireNonNull(classLoader.getResource("Paystub.pdf")).getPath());
                uploadSmartDocument.uploadDocument(
                    folderId, Objects.requireNonNull(classLoader.getResource("Appraisal.pdf")).getPath());
            } else {
                DuReport latestReport = folderService.getLatestReport(folderId, DU);
                initialDocuments = latestReport.getReportData().getDocuments().size();
            }

            int expectedDocs = initialDocuments + 2;
            UploadUtils.waitDocumentsSizeShouldBe(DOCUMENTS_UPLOAD_TIMEOUT, folderId, expectedDocs);
        }

        DuReport latestReport = folderService.getLatestReport(folderId, DU);
        reportId = latestReport.getId().toString();
    }

    @AfterAll
    public static void logout() {
        folderService.deleteSession();
    }

    @Rule
    public ErrorCollector collector = new ErrorCollector();
    Matcher<Boolean> matchesTrue = IsEqual.equalTo(true);

    @SneakyThrows
    @Test/*AUS-29*/
    public void getFolderByIdWithLastReportTest() {
        verifyAutoHide(folderService.getLatestReport(folderId, DU));
    }

    @SneakyThrows
    @Test/*AUS-90*/
    public void getReportByIdWithLastReportTest() {
        DuReport report = reportService.getReportByID(reportId);
        verifyAutoHide(report);
    }

    @SneakyThrows
    @Test/*AUS-41*/
    public void checkReportsCategories() {
        SoftAssertions softAssert = new SoftAssertions();
        DuReport report = reportService.getReportByID(reportId);
        ArrayListMultimap<String, Integer> messageIdAndCategoryPairs = ArrayListMultimap.create();
        for (CSVRecord record : getDuMappingRecords()) {
            messageIdAndCategoryPairs.put(record.get("Category Name"), Integer.parseInt(record.get("MsgID")));
        }
        List<FindingsGroup> findingsGroups = report.getReportData().getFindingsGroups();
        for (FindingsGroup group : findingsGroups) {
            String groupName = group.getName();
            List<Integer> findingIds = group.getFindingIds();
            for (Integer id : findingIds) {
                softAssert.assertThat(messageIdAndCategoryPairs.containsKey(groupName)).as("Incorrect group name \"" + groupName + "\"").isEqualTo(true);
                softAssert.assertThat(id).as("Incorrect DU message in " + groupName + " group. Message id - " + id).isIn(messageIdAndCategoryPairs.get(groupName));
            }
        }
        softAssert.assertAll();
    }

    @SneakyThrows
    @Test/*AUS-42*/
    public void markAsReadApiTest() {
        String msgId = null;
        DuReport duReport = folderService.getLatestReport(folderId, DU);

        Map<String, Finding> findingsMap = duReport.getReportData().getFindings();
        for (String key : findingsMap.keySet()) {
            Finding finding = findingsMap.get(key);
            if (!finding.getRead() & finding.getVisible()) {
                msgId = key;
                break;
            }
        }
        assertNotNull(msgId, "Folder doesn't contain unreaded message");
        assertFalse(findingsMap.get(msgId).getRead(), "Message is reviewed before mark as read request");

        String reportId = duReport.getId().toString();
        reportService.markFindingsRead(msgId, folderId, DU, reportId);
        findingsMap = reportService.getReportByID(reportId).getReportData().getFindings();

        assertTrue(findingsMap.get(msgId).getRead(), "Message wasn't be reviewed after mark as read request");
    }

    @Test/*AUS-30*/
    public void markAsReviewedDocumentsAfterReloadTest() {
        Document document;
        String docName = null;
        String docId = null;

        HashMap<String, Document> documents = folderService.getLatestReport(folderId, DU).getReportData().getDocuments();

        for (String key : documents.keySet()) {
            document = documents.get(key);
            if (!document.isReviewed()) {
                docId = key;
                docName = document.getType();
                break;
            }
        }

        assertNotNull(docId, "Folder doesn't contain not reviewed document");
        assertFalse(documents.get(docId).isReviewed(), "Document is reviewed before mark as read request");

        folderService.markDocumentReviewed(docId, true, folderId);

        String finalDocId = docId;
        Retry.whileTrue(POLLING_INTERVAL, DOCUMENT_REVIEW_TIMEOUT, () -> {
                HashMap<String, Document> docs = folderService.getLatestReport(folderId, DU).getReportData().getDocuments();
            return !docs.get(finalDocId).isReviewed();
            }, "Document wasn't be reviewed after mark as review request. DocId:" + docId + ", folderId:" + folderId
        );

        for (String key : documents.keySet()) {
            document = documents.get(key);
            if (document.getType().equals(docName)) {
                docId = key;
                break;
            }
        }

        int initialDocumentsSize = documents.size();
        uploadSmartDocument.uploadDocument(
            folderId,
            Objects.requireNonNull(AusApiTest.class.getClassLoader().getResource(docName + ".pdf")).getPath()
        );

        int expectedDocs = initialDocumentsSize + 1;
        UploadUtils.waitDocumentsSizeShouldBe(DOCUMENTS_UPLOAD_TIMEOUT, folderId, expectedDocs);

        documents = folderService.getLatestReport(folderId, DU).getReportData().getDocuments();

        for (String key : documents.keySet()) {
            document = documents.get(key);
            if (document.getType().equals(docName) & !document.isReviewed()) {
                docId = key;
                break;
            }
        }
        assertFalse(documents.get(docId).isReviewed(), "Document is reviewed after reload");
    }


    @SneakyThrows
    @Test
    public void duMapping() {
        String[] headers = {
            "Message Status for DU10.3", "DU/DU identifier", "MsgID", "Name", "Severity", "Category", "Category Name",
            "Type", "Lender Text", "Boiler Plate", "Doc Association", "Documents to Associate"
        };

        List<CSVRecord> records = CSVUtils.getRecords("du-mapping.csv", headers, ';');
        List<CSVRecord> doc_association = records.stream().filter(record -> "Y".equals(record.get("Doc Association"))).collect(Collectors.toList());

        //TODO
        doc_association.forEach(r -> log.info(r.toString()));
    }

    private void verifyAutoHide(DuReport report) throws IOException {
        List<CSVRecord> csvRecords = CSVUtils.getRecords(DU_CSV_FILE, DU_CSV_HEADERS);
        Map<String, Finding> findings = report.getReportData().getFindings();

        for (Finding finding : findings.values()) {
            Boolean expected =
                Boolean.valueOf(
                    csvRecords
                        .stream()
                        .filter(record -> Objects.equals(String.valueOf(finding.getId()), record.get("id")))
                        .findFirst()
                        .orElseThrow(() -> new AssertionError("Unknown DU finding id: " + finding.getId()))
                        .get("visible")
                );

            assertEquals(expected, finding.getVisible(),
                "Finding id: " + finding.getId() + " visible should be " + expected + ", but found " + finding.getVisible());
        }
    }

}