package com.capsilon.automation.aus.rest;

import com.capsilon.automation.aus.dto.DuReport;
import com.capsilon.automation.bam.rest.admin.BizAppHttpStatusCodes;
import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;


public class ReportsService extends BaseRestService {

    public static final String API_SERVICE = "/bizapps/aus-analyzer/api/reports";

    public DuReport getReportByID(String id) {
        return given()
            .contentType(CONTENT_TYPE)
            .headers(headersMap)
            .when()
            .get(API_ROOT + API_SERVICE + "/" + id)
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode())
            .extract().response().as((Type) DuReport.class);
    }

    @SneakyThrows
    public Response markFindingsRead(String msgId, String folderId, ReportType reportType, String reportId) {
        return given()
            .log().parameters()
            .log().method()
            .log().uri()
            .contentType(CONTENT_TYPE)
            .headers(headersMap)
            .cookies(getCookies())
            .body("{\"folder_id\":" + folderId + ",\"report_type\":\"" + reportType + "\",\"ids\":[" + msgId + "]}")
            .when()
            .post(API_ROOT + API_SERVICE + "/" + reportId + "/mark-findings-read")
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode())
            .extract().response();
    }
}
