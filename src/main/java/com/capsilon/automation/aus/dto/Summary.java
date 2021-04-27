
package com.capsilon.automation.aus.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recommendation",
    "primary_borrower",
    "co_borrower",
    "lender_loan_number",
    "submission_date",
    "case_file_id",
    "submitted_by",
    "du_version",
    "submission_number",
    "mortgage_information",
    "property_information"
})
public class Summary {

    @JsonProperty("recommendation")
    private String recommendation;
    @JsonProperty("primary_borrower")
    private String primaryBorrower;
    @JsonProperty("co_borrower")
    private String coBorrower;
    @JsonProperty("lender_loan_number")
    private String lenderLoanNumber;
    @JsonProperty("submission_date")
    private String submissionDate;
    @JsonProperty("case_file_id")
    private Integer caseFileId;
    @JsonProperty("submitted_by")
    private String submittedBy;
    @JsonProperty("du_version")
    private String duVersion;
    @JsonProperty("submission_number")
    private Integer submissionNumber;
    @JsonProperty("mortgage_information")
    private MortgageInformation mortgageInformation;
    @JsonProperty("property_information")
    private PropertyInformation propertyInformation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("recommendation")
    public String getRecommendation() {
        return recommendation;
    }

    @JsonProperty("recommendation")
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    @JsonProperty("primary_borrower")
    public String getPrimaryBorrower() {
        return primaryBorrower;
    }

    @JsonProperty("primary_borrower")
    public void setPrimaryBorrower(String primaryBorrower) {
        this.primaryBorrower = primaryBorrower;
    }

    @JsonProperty("co_borrower")
    public String getCoBorrower() {
        return coBorrower;
    }

    @JsonProperty("co_borrower")
    public void setCoBorrower(String coBorrower) {
        this.coBorrower = coBorrower;
    }

    @JsonProperty("lender_loan_number")
    public String getLenderLoanNumber() {
        return lenderLoanNumber;
    }

    @JsonProperty("lender_loan_number")
    public void setLenderLoanNumber(String lenderLoanNumber) {
        this.lenderLoanNumber = lenderLoanNumber;
    }

    @JsonProperty("submission_date")
    public String getSubmissionDate() {
        return submissionDate;
    }

    @JsonProperty("submission_date")
    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    @JsonProperty("case_file_id")
    public Integer getCaseFileId() {
        return caseFileId;
    }

    @JsonProperty("case_file_id")
    public void setCaseFileId(Integer caseFileId) {
        this.caseFileId = caseFileId;
    }

    @JsonProperty("submitted_by")
    public String getSubmittedBy() {
        return submittedBy;
    }

    @JsonProperty("submitted_by")
    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    @JsonProperty("du_version")
    public String getDuVersion() {
        return duVersion;
    }

    @JsonProperty("du_version")
    public void setDuVersion(String duVersion) {
        this.duVersion = duVersion;
    }

    @JsonProperty("submission_number")
    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    @JsonProperty("submission_number")
    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    @JsonProperty("mortgage_information")
    public MortgageInformation getMortgageInformation() {
        return mortgageInformation;
    }

    @JsonProperty("mortgage_information")
    public void setMortgageInformation(MortgageInformation mortgageInformation) {
        this.mortgageInformation = mortgageInformation;
    }

    @JsonProperty("property_information")
    public PropertyInformation getPropertyInformation() {
        return propertyInformation;
    }

    @JsonProperty("property_information")
    public void setPropertyInformation(PropertyInformation propertyInformation) {
        this.propertyInformation = propertyInformation;
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
