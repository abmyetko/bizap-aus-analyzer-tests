package com.capsilon.automation.aus.rest;

import com.capsilon.automation.aus.dto.fileddocs.FiledDocs;
import com.capsilon.automation.aus.rest.appTicketGeneration.GenerateAppTickets;
import com.capsilon.automation.aus.rest.appTicketGeneration.ValidateApplicationAndSiteService;
import com.capsilon.automation.bam.rest.admin.BizAppHttpStatusCodes;
import lombok.SneakyThrows;

import java.sql.Timestamp;

import static io.restassured.RestAssured.given;

public class DocumentInterfaceService extends BaseRestService {

    public static final String API_SERVICE = "/services/rest/DocumentInterface";

    private static final ValidateApplicationAndSiteService validateApplicationAndSiteService = new ValidateApplicationAndSiteService();
    private static final GenerateAppTickets generateAppTickets = new GenerateAppTickets();

    @SneakyThrows
    public FiledDocs getAllDocuments(String folderId) {
        String baseUrl = validateApplicationAndSiteService.validateApplicationAndSite().getUrlByKey("gatewayBaseURL");
        String applicationTicketId = generateAppTickets.getApplicationTicketId();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String dvFolderIdLong = getFolderIdLong(folderId);

        return given()
            .formParam("applicationTicketId", applicationTicketId)
            .formParam("folderId", dvFolderIdLong)
            .formParam("timestamp", timestamp)
            .when()
            .get(baseUrl + API_SERVICE + "/getAllDocuments")
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode())
            .extract().response().getBody().as(FiledDocs.class);
    }
}
