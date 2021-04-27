package com.capsilon.automation.aus.dto.uploadDocuments;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SmartDocument {
    private String documentDictionaryName;
    private String docTypeName;
    private String typeDescription;
    private String hint;
    private String category;
    private String displayStatus;
    private String signed;
    private String documentTags;
    private Payloads payloads;

    @JacksonXmlProperty(isAttribute = true)
    public String getDocumentDictionaryName() {
        return documentDictionaryName;
    }

    @JacksonXmlProperty(isAttribute = true)
    public String getDocTypeName() {
        return docTypeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public String getHint() {
        return hint;
    }

    public String getCategory() {
        return category;
    }

    public String getDisplayStatus() {
        return displayStatus;
    }

    public String getSigned() {
        return signed;
    }

    public String getDocumentTags() {
        return documentTags;
    }

    public Payloads getPayloads() {
        return payloads;
    }


    public void setDocumentDictionaryName(String documentDictionaryName) {
        this.documentDictionaryName = documentDictionaryName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDisplayStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }

    public void setDocumentTags(String documentTags) {
        this.documentTags = documentTags;
    }

    public void setPayloads(Payloads payloads) {
        this.payloads = payloads;
    }


}
