package com.capsilon.automation.aus.dto.fileddocs;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
class PageInfoList {
    private String id;
    private List<Object> meta;
    private List<Object> redactions;
    private String pageId;
}

@Data
public class FiledDoc {
    private String id;
    private String parentId;
    private Date dateCreated;
    private Date dateModified;
    private String friendlyId;
    private String typeName;
    private String typeDescription;
    private String documentDictionaryName;
    private String category; // "Income/Assets"
    private String hint;
    private String signed;
    private String active;
    private int pageCount;
    private List<PageInfoList> pageInfoList;
    private Date dateClientReceived;
    private String sender;
    private Date dateReceived;
    private boolean accepted;
    private boolean decisionDocument;
    private List<Object> documentTags;
    private String creator;
    private int noteCount;
    private boolean dataOnly;
    private String dataFileId;
    private String dataFileType;
    private int viewCount;
    private String processingStatus;
    private String revision;
    private List<Object> processingStatusList;
    private boolean internalAnnotationsPresent;
    private boolean externalAnnotationsPresent;
    private boolean containsSignatureInfo;
    private String mailitemFriendlyId;
    private String activeVersion;
    private String autoVersioning;
    private List<Object> collaboratorIds;
    private boolean excludedFromValidation;
    private boolean dataIngested;
    private String extractionHash;
    private boolean reviewed;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

