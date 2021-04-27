package com.capsilon.automation.aus.dto.uploadDocuments;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SmartDocumentFile {

    private String payloadId;
    private String name;
    private String type;

    @JacksonXmlProperty(isAttribute = true)
    public String getPayloadId() {
        return payloadId;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getName() {
        return name;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getType() {
        return type;
    }

    public void setPayloadId(String payloadId) {
        this.payloadId = payloadId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
