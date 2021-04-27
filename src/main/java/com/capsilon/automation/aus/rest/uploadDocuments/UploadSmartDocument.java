package com.capsilon.automation.aus.rest.uploadDocuments;


import com.capsilon.automation.aus.rest.BaseRestService;
import com.capsilon.automation.aus.rest.appTicketGeneration.GenerateAppTickets;
import io.restassured.path.xml.XmlPath;
import lombok.SneakyThrows;

import java.io.File;

public class UploadSmartDocument extends BaseRestService {

    private static final MailItemInterfaceService mailItemInterfaceService = new MailItemInterfaceService();
    private static final UploadService uploadService = new UploadService();
    private static final GenerateAppTickets generateAppTickets = new GenerateAppTickets();

    @SneakyThrows
    public void uploadSmartXml(String folderId, String xmlFilePath, String fileType) {
        String appTicketId = generateAppTickets.getApplicationTicketId();
        String contextId = getFolderIdLong(folderId);

        String transmission = mailItemInterfaceService.startTransmission(appTicketId);
        File file = new File(xmlFilePath);

        XmlPath upload = uploadService.httpUpload(appTicketId, file, transmission);
        mailItemInterfaceService.uploadSmartXmlDocument(appTicketId, contextId, upload.getString("response.files.file.@id"), transmission, fileType);
    }

    @SneakyThrows
    public void uploadDocument(String folderId, String filePath) {
        String appTicketId = generateAppTickets.getApplicationTicketId();
        String contextId = getFolderIdLong(folderId);

        String transmission = mailItemInterfaceService.startTransmission(appTicketId);
        File file = new File(filePath);

        XmlPath upload = uploadService.httpUpload(appTicketId, file, transmission);
        mailItemInterfaceService.uploadDocument(appTicketId, contextId, upload.getString("response.files.file.@id"), transmission, file.getName());
    }
}
