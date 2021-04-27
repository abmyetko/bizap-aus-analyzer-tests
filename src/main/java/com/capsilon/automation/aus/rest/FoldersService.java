package com.capsilon.automation.aus.rest;

import com.capsilon.automation.aus.dto.DuReport;
import com.capsilon.automation.aus.dto.FolderWithReports;
import com.capsilon.automation.aus.utils.ReportUtils;
import com.capsilon.automation.bam.rest.admin.BizAppHttpStatusCodes;
import io.restassured.response.Response;
import lombok.SneakyThrows;

import static io.restassured.RestAssured.given;

public class FoldersService extends BaseRestService {

    public static final String API_SERVICE = "/bizapps/aus-analyzer/api/folders";

    @SneakyThrows
    public FolderWithReports getFolderWithReports(String folderId) {
        return given()
            .contentType(CONTENT_TYPE)
            .headers(headersMap)
            .when()
            .get(API_ROOT + API_SERVICE + "/" + folderId + "?include=latest_report")
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode())
            .extract().response().as(FolderWithReports.class);
    }

    @SneakyThrows
    public Response markDocumentReviewed(String documentId, boolean isReviewed, String folderId) {
        return given()
            .contentType(CONTENT_TYPE)
            .headers(headersMap)
            .cookies(getCookies())
            .body("{\"document_id\": \"" + documentId + "\", \"reviewed\": " + isReviewed + "}")
            .when()
            .post(API_ROOT + API_SERVICE + "/" + folderId + "/mark-document-reviewed")
            .then()
            .extract().response();
    }

    public DuReport getLatestReport(String folderId, ReportType reportType) {
        FolderWithReports folderWithReports = getFolderWithReports(folderId);
        return ReportUtils.getLatestReport(folderWithReports, reportType);
    }

}
