
package com.capsilon.automation.aus.dto;

import com.capsilon.automation.aus.rest.ReportType;
import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FolderWithReports {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("du_all_report_infos")
    private List<AllReportInfo> duAllReportInfos = new ArrayList<>();
    @JsonProperty("lpa_all_report_infos")
    private List<AllReportInfo> lpaAllReportInfos = new ArrayList<>();
    @JsonProperty("latest_reports")
    private List<DuReport> latestReports = new ArrayList<>();
    @JsonProperty("primary_report_type")
    private ReportType primaryReportType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AllReportInfo> getDuAllReportInfos() {
        return duAllReportInfos;
    }

    public void setDuAllReportInfos(List<AllReportInfo> duAllReportInfos) {
        this.duAllReportInfos = duAllReportInfos;
    }

    public List<DuReport> getLatestReports() {
        return latestReports;
    }

    public void setLatestReports(List<DuReport> latestReports) {
        this.latestReports = latestReports;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public List<AllReportInfo> getLpaAllReportInfos() {
        return lpaAllReportInfos;
    }

    public void setLpaAllReportInfos(List<AllReportInfo> lpaAllReportInfos) {
        this.lpaAllReportInfos = lpaAllReportInfos;
    }

    public ReportType getPrimaryReportType() {
        return primaryReportType;
    }

    public void setPrimaryReportType(ReportType primaryReportType) {
        this.primaryReportType = primaryReportType;
    }
}
