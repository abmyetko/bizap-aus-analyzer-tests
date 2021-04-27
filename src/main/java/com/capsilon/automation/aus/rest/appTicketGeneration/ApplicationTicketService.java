package com.capsilon.automation.aus.rest.appTicketGeneration;

import com.capsilon.automation.aus.dto.appTicketGeneration.ApplicationTicketCollection;
import com.capsilon.automation.aus.dto.appTicketGeneration.ValidateApplicationAndSiteResult;
import com.capsilon.automation.aus.wrappers.ApplicationTicketCollectionWrapper;
import com.capsilon.automation.bam.rest.admin.BizAppHttpStatusCodes;
import lombok.SneakyThrows;

import static com.capsilon.test.run.confiuration.BizappsConfig.getString;
import static io.restassured.RestAssured.given;

class ApplicationTicketService {

    private static final String APPLICATION_NAME = getString("dv.site-properties-by-site-guid.test-site-guid.application-name");
    private static final String APPLICATION_SECRET = getString("dv.site-properties-by-site-guid.test-site-guid.application-secret");

    private static final String API_SERVICE = "/services/rest/ApplicationTicketInterface/clearAndGenerateApplicationTicketsCollection";

    @SneakyThrows
    ApplicationTicketCollection generateApplicationTickets(String numberOfTickets, String userTicketId, ValidateApplicationAndSiteResult validateResult) {
        String baseUrl = validateResult.getUrlByKey("gatewayBaseURL");
        return given()
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .header("userTicketId", userTicketId)
            .formParam("noOfTickets", numberOfTickets)
            .formParam("applicationSecret", APPLICATION_SECRET)
            .formParam("applicationName", APPLICATION_NAME)
            .when()
            .post(baseUrl + API_SERVICE)
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode())
            .extract().response().as(ApplicationTicketCollectionWrapper.class).getApplicationTicketCollection();
    }
}
