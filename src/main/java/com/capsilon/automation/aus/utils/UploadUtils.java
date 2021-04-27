package com.capsilon.automation.aus.utils;

import com.capsilon.automation.aus.dto.DuReport;
import com.capsilon.automation.aus.rest.FoldersService;
import com.capsilon.test.ui.Retry;
import lombok.SneakyThrows;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URL;
import java.util.Objects;

import static com.capsilon.automation.aus.rest.ReportType.DU;

public class UploadUtils {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UploadUtils.class);

    public static void waitDocumentsSizeShouldBe(Integer timeout, String folderId, Integer size) {
        FoldersService service = new FoldersService();
        int pollingInterval = 3000;

        Retry.whileTrue(pollingInterval, timeout, () -> {
                DuReport report = service.getLatestReport(folderId, DU);
                int documentsCount = ReportUtils.getDocuments(report).size();
                log.info("Filed docs in folder: " + documentsCount);
                return documentsCount < size;
            }, "Docs list size should be " + size
        );
    }

    @SneakyThrows
    public static String getFilePath(String fileName) {
        URL resource = UploadUtils.class.getClassLoader().getResource(fileName);
        URI uri = new URI(Objects.requireNonNull(resource).toString());
        return uri.getPath();
    }
}
