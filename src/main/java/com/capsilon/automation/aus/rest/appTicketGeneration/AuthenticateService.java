package com.capsilon.automation.aus.rest.appTicketGeneration;

import com.capsilon.automation.aus.dto.appTicketGeneration.NamedUserAuthenticationResult;
import com.capsilon.automation.aus.dto.appTicketGeneration.ValidateApplicationAndSiteResult;
import com.capsilon.automation.aus.utils.Encryptor;
import com.capsilon.automation.aus.wrappers.NamedUserAuthenticationResultWrapper;
import com.capsilon.automation.bam.rest.admin.BizAppHttpStatusCodes;
import lombok.SneakyThrows;

import static com.capsilon.test.run.confiuration.BizappsConfig.getString;
import static io.restassured.RestAssured.given;


class AuthenticateService {

    private String fingerPrint;
    private String otcData;
    private String credentialData;

    private static final String USER_NAME = getString("bizapps.siteAdmin");
    private static final String PASSWORD = getString("bizapps.siteAdminPassword");
    private static final String APPLICATION_NAME = getString("dv.site-properties-by-site-guid.test-site-guid.application-name");
    private static final String CERTIFICATE_ID = getString("dv.site-properties-by-site-guid.test-site-guid.certificate-id");
    private static final String PUBLIC_KEY = getString("dv.site-properties-by-site-guid.test-site-guid.public-key");

    private

    String baseUrl;
    private static final String API_SERVICE = "/services/rest/UserTicketInterface/authenticate";

    @SneakyThrows
    private NamedUserAuthenticationResult authenticate(String userName, String applicationName, ValidateApplicationAndSiteResult validateResult, String password) {
        baseUrl = validateResult.getUrlByKey("gatewayBaseURL");
        fingerPrint = Encryptor.generateFingerPrint(applicationName);
        otcData = Encryptor.getOtcData(fingerPrint, validateResult.getResponseId(), password);
        credentialData = Encryptor.getCredentialData(fingerPrint,
            validateResult.getResponseId(),
            password,
            PUBLIC_KEY,
            CERTIFICATE_ID);
        return given()
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .formParam("principal", userName)
            .formParam("otcData", otcData)
            .formParam("applicationName", applicationName)
            .formParam("siteId", validateResult.getSiteId())
            .formParam("credentialData", credentialData)
            .when()
            .post(baseUrl + API_SERVICE)
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode())
            .extract().response().as(NamedUserAuthenticationResultWrapper.class).getNamedUserAuthenticationResult();
    }

    @SneakyThrows
    NamedUserAuthenticationResult authenticate(ValidateApplicationAndSiteResult validateResult) {
        return authenticate(USER_NAME, APPLICATION_NAME, validateResult, PASSWORD);
    }

}
