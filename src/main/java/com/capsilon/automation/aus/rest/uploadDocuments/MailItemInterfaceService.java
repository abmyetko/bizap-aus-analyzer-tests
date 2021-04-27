package com.capsilon.automation.aus.rest.uploadDocuments;

import com.capsilon.automation.aus.dto.uploadDocuments.*;
import com.capsilon.automation.aus.rest.appTicketGeneration.ValidateApplicationAndSiteService;
import com.capsilon.automation.bam.rest.admin.BizAppHttpStatusCodes;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.json.simple.parser.ParseException;

import java.sql.Timestamp;

import static io.restassured.RestAssured.given;

public class MailItemInterfaceService {

    private static final ValidateApplicationAndSiteService validateApplicationAndSiteService = new ValidateApplicationAndSiteService();
    public static final String API_SERVICE = "/services/rest/MailitemInterface";

    public String startTransmission(String applicationTicketId) throws ParseException {
        String baseUrl = validateApplicationAndSiteService.validateApplicationAndSite().getUrlByKey("gatewayBaseURL");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return given()
            .formParam("applicationTicketId", applicationTicketId)
            .formParam("timestamp", timestamp)
            .when()
            .get(baseUrl + API_SERVICE + "/startTransmission")
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode())
            .extract().response().getBody().print();
    }

    @SneakyThrows
    public String uploadSmartXmlDocument(String applicationTicketId, String folderId, String payloadId, String transmission, String fileType) throws ParseException {
        String baseUrl = validateApplicationAndSiteService.validateApplicationAndSite().getUrlByKey("gatewayBaseURL");

        XmlMapper xmlMapper = new XmlMapper();

        SmartDocumentFile smartDocumentFile = new SmartDocumentFile();
        smartDocumentFile.setName("Unknown (du-findings.xml)");
        smartDocumentFile.setType(fileType);
        smartDocumentFile.setPayloadId(payloadId);

        Payloads payloads = new Payloads();
        payloads.setSmartDocumentFile(smartDocumentFile);

        SmartDocument smartDocument = new SmartDocument();
        smartDocument.setDocTypeName("Unknown");
        smartDocument.setDocumentDictionaryName("standard");
        smartDocument.setHint("du-findings.xml");
        smartDocument.setPayloads(payloads);

        SmartDocuments smartDocuments = new SmartDocuments();
        smartDocuments.setSmartDocument(smartDocument);

        String xml = xmlMapper.writeValueAsString(smartDocuments);

        return given()
            .formParam("applicationTicketId", applicationTicketId)
            .formParam("channel", "Document Submission Application")
            .formParam("sendByChannel", "")
            .formParam("autocreatedocuments", "true")
            .formParam("indexingoption", "INDEX_BY_SENDER")
            .formParam("transmissionid", transmission)
            .formParam("contextId", folderId)
            .formParam("subject", "Autotest Subject")
            .formParam("note", "Autotest Note")
            .formParam("documentDetailXML", xml.replace("SmartDocuments", "smartDocuments"))
            .when()
            .post(baseUrl + API_SERVICE + "/uploadSmartDocuments")
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode())
            .extract().response().getBody().print();
    }

    @SneakyThrows
    public String uploadDocument(String applicationTicketId, String folderId, String payloadId, String transmission, String documentName) {
        String baseUrl = validateApplicationAndSiteService.validateApplicationAndSite().getUrlByKey("gatewayBaseURL");

        XmlMapper xmlMapper = new XmlMapper();

        Document document = new Document();
        document.setDocTypeName("Unknown");
        document.setDocumentDictionaryName("standard");
        document.setHint(documentName);
        document.setPayloadId(payloadId);

        Documents documents = new Documents();
        documents.setDocument(document);

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xmlMapper.writeValueAsString(documents);

        return given()
            .formParam("applicationTicketId", applicationTicketId)
            .formParam("channel", "Document Submission Application")
            .formParam("sendByChannel", "")
            .formParam("autocreatedocuments", "true")
            .formParam("indexingoption", "AUTO_INDEXING")
            .formParam("transmissionid", transmission)
            .formParam("contextId", folderId)
            .formParam("subject", "Autotest Subject")
            .formParam("note", "Autotest Note")
            .formParam("documentDetailXML", xml.replace("Documents", "documents"))
            .when()
            .post(baseUrl + API_SERVICE + "/uploadMailItem")
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode())
            .extract().response().getBody().print();
    }


}
