package com.capsilon.automation.aus.rest.appTicketGeneration;

import com.capsilon.automation.aus.dto.appTicketGeneration.ValidateApplicationAndSiteResult;
import com.capsilon.automation.aus.wrappers.ValidateApplicationAndSiteResultWrapper;
import com.capsilon.automation.bam.rest.admin.BizAppHttpStatusCodes;
import lombok.SneakyThrows;

import java.lang.reflect.Type;

import static com.capsilon.test.run.confiuration.BizappsConfig.getString;
import static io.restassured.RestAssured.given;

public class ValidateApplicationAndSiteService {

    private static final String BASE_URL = getString("dv.site-properties-by-site-guid.test-site-guid.hub-url");
    private static final String APPLICATION_NAME = getString("dv.site-properties-by-site-guid.test-site-guid.application-name");
    private static final String APPLICATION_SECRET = getString("dv.site-properties-by-site-guid.test-site-guid.application-secret");
    private static final String SITE_ADDRESS = getString("dv.site-properties-by-site-guid.test-site-guid.site-id");

    private static final String API_SERVICE = "/rest/ApplicationService/validateApplicationAndSite";

    @SneakyThrows
    public ValidateApplicationAndSiteResult validateApplicationAndSite() {
        ValidateApplicationAndSiteResultWrapper validateApplicationAndSiteResultWrapper = given()
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .formParam("siteAddress", SITE_ADDRESS)
            .formParam("applicationName", APPLICATION_NAME)
            .formParam("applicationSecret", APPLICATION_SECRET)
            .when()
            .post(BASE_URL + API_SERVICE)
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode())
            .extract().body().as((Type) ValidateApplicationAndSiteResultWrapper.class);
        return validateApplicationAndSiteResultWrapper.getValidateApplicationAndSiteResult();
    }

}
