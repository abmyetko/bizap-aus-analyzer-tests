
package com.capsilon.automation.aus.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "version",
    "timestamp",
    "summary",
    "findings_groups",
    "findings",
    "income",
    "qualifying_ratios",
    "expense_ratios",
    "proposed_monthly_payment",
    "funds",
    "dv_folder_id"
})
public class ReportData {

    @JsonProperty("name")
    private String name;
    @JsonProperty("version")
    private Object version;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("summary")
    private Summary summary;
    @JsonProperty("findings_groups")
    private List<FindingsGroup> findingsGroups = new ArrayList<>();
    @JsonProperty("findings")
    private Map<String, Finding> findings;
    @JsonProperty("income")
    private Income income;
    @JsonProperty("qualifying_ratios")
    private QualifyingRatios qualifyingRatios;
    @JsonProperty("expense_ratios")
    private ExpenseRatios expenseRatios;
    @JsonProperty("proposed_monthly_payment")
    private ProposedMonthlyPayment proposedMonthlyPayment;
    @JsonProperty("funds")
    private Funds funds;
    @JsonProperty("dv_folder_id")
    private String dvFolderId;
    @JsonProperty("documents")
    private HashMap<String, Document> documents = new HashMap<>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("version")
    public Object getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Object version) {
        this.version = version;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("summary")
    public Summary getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    @JsonProperty("findings_groups")
    public List<FindingsGroup> getFindingsGroups() {
        return findingsGroups;
    }

    @JsonProperty("findings_groups")
    public void setFindingsGroups(List<FindingsGroup> findingsGroups) {
        this.findingsGroups = findingsGroups;
    }

    @JsonProperty("findings")
    public Map<String, Finding> getFindings() {
        return findings;
    }

    @JsonProperty("findings")
    public void setFindings(Map<String, Finding> findings) {
        this.findings = findings;
    }

    @JsonProperty("income")
    public Income getIncome() {
        return income;
    }

    @JsonProperty("income")
    public void setIncome(Income income) {
        this.income = income;
    }

    @JsonProperty("qualifying_ratios")
    public QualifyingRatios getQualifyingRatios() {
        return qualifyingRatios;
    }

    @JsonProperty("qualifying_ratios")
    public void setQualifyingRatios(QualifyingRatios qualifyingRatios) {
        this.qualifyingRatios = qualifyingRatios;
    }

    @JsonProperty("expense_ratios")
    public ExpenseRatios getExpenseRatios() {
        return expenseRatios;
    }

    @JsonProperty("expense_ratios")
    public void setExpenseRatios(ExpenseRatios expenseRatios) {
        this.expenseRatios = expenseRatios;
    }

    @JsonProperty("proposed_monthly_payment")
    public ProposedMonthlyPayment getProposedMonthlyPayment() {
        return proposedMonthlyPayment;
    }

    @JsonProperty("proposed_monthly_payment")
    public void setProposedMonthlyPayment(ProposedMonthlyPayment proposedMonthlyPayment) {
        this.proposedMonthlyPayment = proposedMonthlyPayment;
    }

    @JsonProperty("funds")
    public Funds getFunds() {
        return funds;
    }

    @JsonProperty("funds")
    public void setFunds(Funds funds) {
        this.funds = funds;
    }

    @JsonProperty("dv_folder_id")
    public String getDvFolderId() {
        return dvFolderId;
    }

    @JsonProperty("dv_folder_id")
    public void setDvFolderId(String dvFolderId) {
        this.dvFolderId = dvFolderId;
    }

    @JsonProperty("documents")
    public HashMap<String, Document> getDocuments() {
        return documents;
    }

    @JsonProperty("documents")
    public void setDocuments(HashMap<String, Document> documents) {
        this.documents = documents;
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
