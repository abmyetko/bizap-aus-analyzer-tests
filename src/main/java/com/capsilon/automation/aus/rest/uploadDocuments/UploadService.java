package com.capsilon.automation.aus.rest.uploadDocuments;

import com.capsilon.automation.aus.rest.appTicketGeneration.ValidateApplicationAndSiteService;
import com.capsilon.automation.bam.rest.admin.BizAppHttpStatusCodes;
import io.restassured.path.xml.XmlPath;
import lombok.SneakyThrows;
import org.json.simple.parser.ParseException;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadService {

    private static final ValidateApplicationAndSiteService validateApplicationAndSiteService = new ValidateApplicationAndSiteService();

    @SneakyThrows
    public XmlPath httpUpload(String applicationTicketId, File file, String transmissionId) throws ParseException {
        String baseUrl = validateApplicationAndSiteService.validateApplicationAndSite().getUrlByKey("gatewayBaseURL");
        String stringResponse = given()
            .multiPart("file", file)
            .formParam("transmissionId", transmissionId)
            .formParam("applicationTicketId", applicationTicketId)
            .when()
            .post(baseUrl.replace("/RESTGateway", "") + "/upload-gateway/HttpUpload")
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode())
            .extract().response().body().print();
        return new XmlPath(stringResponse);
    }

}
