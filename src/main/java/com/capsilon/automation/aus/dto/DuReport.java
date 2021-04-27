
package com.capsilon.automation.aus.dto;

import com.capsilon.automation.aus.rest.ReportType;
import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DuReport {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("folder_id")
    private Integer folderId;
    @JsonProperty("report_data")
    private ReportData reportData;
    @JsonProperty("report_type")
    private ReportType reportType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("folder_id")
    public Integer getFolderId() {
        return folderId;
    }

    @JsonProperty("folder_id")
    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    @JsonProperty("report_data")
    public ReportData getReportData() {
        return reportData;
    }

    @JsonProperty("report_data")
    public void setReportData(ReportData reportData) {
        this.reportData = reportData;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }
}
