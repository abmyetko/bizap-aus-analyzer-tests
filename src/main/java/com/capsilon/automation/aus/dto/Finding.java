package com.capsilon.automation.aus.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Finding {

    @JsonProperty("documents_ids")
    private List<String> documentsIds;
    private int id;
    private String message;
    private Boolean read;
    private Boolean visible;
    @JsonProperty("message_table")
    private Object messageTable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public List<String> getDocuments() {
        return documentsIds;
    }

    public void setDocuments(List<String> documents) {
        this.documentsIds = documents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Object getMessageTable() {
        return messageTable;
    }

    public void setMessageTable(Object messageTable) {
        this.messageTable = messageTable;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
