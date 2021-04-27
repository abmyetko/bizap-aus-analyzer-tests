
package com.capsilon.automation.aus.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loan_to_value",
    "combined_loan_to_value",
    "housing_expense_ratio",
    "total_expense_ratio",
    "loan_amount",
    "total_loan_amount",
    "financed_miamount",
    "sales_price",
    "appraisal_value",
    "note_rate",
    "bought_down_rate",
    "qualifying_rate",
    "loan_type",
    "loan_term",
    "amortization_type",
    "loan_purpose",
    "refi_purpose",
    "balloon",
    "community_lending",
    "payment_frequency",
    "lien_type",
    "amt_subordinate_fin",
    "owner_existing_mtg_buydown",
    "pand_i"
})
public class MortgageInformation {

    @JsonProperty("loan_to_value")
    private Double loanToValue;
    @JsonProperty("combined_loan_to_value")
    private Double combinedLoanToValue;
    @JsonProperty("housing_expense_ratio")
    private Double housingExpenseRatio;
    @JsonProperty("total_expense_ratio")
    private Double totalExpenseRatio;
    @JsonProperty("loan_amount")
    private Double loanAmount;
    @JsonProperty("total_loan_amount")
    private Double totalLoanAmount;
    @JsonProperty("financed_miamount")
    private Double financedMiamount;
    @JsonProperty("sales_price")
    private Double salesPrice;
    @JsonProperty("appraisal_value")
    private Double appraisalValue;
    @JsonProperty("note_rate")
    private Double noteRate;
    @JsonProperty("bought_down_rate")
    private Double boughtDownRate;
    @JsonProperty("qualifying_rate")
    private Double qualifyingRate;
    @JsonProperty("loan_type")
    private String loanType;
    @JsonProperty("loan_term")
    private Integer loanTerm;
    @JsonProperty("amortization_type")
    private String amortizationType;
    @JsonProperty("loan_purpose")
    private String loanPurpose;
    @JsonProperty("refi_purpose")
    private String refiPurpose;
    @JsonProperty("balloon")
    private String balloon;
    @JsonProperty("community_lending")
    private String communityLending;
    @JsonProperty("payment_frequency")
    private Object paymentFrequency;
    @JsonProperty("lien_type")
    private Object lienType;
    @JsonProperty("amt_subordinate_fin")
    private String amtSubordinateFin;
    @JsonProperty("owner_existing_mtg_buydown")
    private String ownerExistingMtgBuydown;
    @JsonProperty("pand_i")
    private Double pandI;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("loan_to_value")
    public Double getLoanToValue() {
        return loanToValue;
    }

    @JsonProperty("loan_to_value")
    public void setLoanToValue(Double loanToValue) {
        this.loanToValue = loanToValue;
    }

    @JsonProperty("combined_loan_to_value")
    public Double getCombinedLoanToValue() {
        return combinedLoanToValue;
    }

    @JsonProperty("combined_loan_to_value")
    public void setCombinedLoanToValue(Double combinedLoanToValue) {
        this.combinedLoanToValue = combinedLoanToValue;
    }

    @JsonProperty("housing_expense_ratio")
    public Double getHousingExpenseRatio() {
        return housingExpenseRatio;
    }

    @JsonProperty("housing_expense_ratio")
    public void setHousingExpenseRatio(Double housingExpenseRatio) {
        this.housingExpenseRatio = housingExpenseRatio;
    }

    @JsonProperty("total_expense_ratio")
    public Double getTotalExpenseRatio() {
        return totalExpenseRatio;
    }

    @JsonProperty("total_expense_ratio")
    public void setTotalExpenseRatio(Double totalExpenseRatio) {
        this.totalExpenseRatio = totalExpenseRatio;
    }

    @JsonProperty("loan_amount")
    public Double getLoanAmount() {
        return loanAmount;
    }

    @JsonProperty("loan_amount")
    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    @JsonProperty("total_loan_amount")
    public Double getTotalLoanAmount() {
        return totalLoanAmount;
    }

    @JsonProperty("total_loan_amount")
    public void setTotalLoanAmount(Double totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    @JsonProperty("financed_miamount")
    public Double getFinancedMiamount() {
        return financedMiamount;
    }

    @JsonProperty("financed_miamount")
    public void setFinancedMiamount(Double financedMiamount) {
        this.financedMiamount = financedMiamount;
    }

    @JsonProperty("sales_price")
    public Double getSalesPrice() {
        return salesPrice;
    }

    @JsonProperty("sales_price")
    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    @JsonProperty("appraisal_value")
    public Double getAppraisalValue() {
        return appraisalValue;
    }

    @JsonProperty("appraisal_value")
    public void setAppraisalValue(Double appraisalValue) {
        this.appraisalValue = appraisalValue;
    }

    @JsonProperty("note_rate")
    public Double getNoteRate() {
        return noteRate;
    }

    @JsonProperty("note_rate")
    public void setNoteRate(Double noteRate) {
        this.noteRate = noteRate;
    }

    @JsonProperty("bought_down_rate")
    public Double getBoughtDownRate() {
        return boughtDownRate;
    }

    @JsonProperty("bought_down_rate")
    public void setBoughtDownRate(Double boughtDownRate) {
        this.boughtDownRate = boughtDownRate;
    }

    @JsonProperty("qualifying_rate")
    public Double getQualifyingRate() {
        return qualifyingRate;
    }

    @JsonProperty("qualifying_rate")
    public void setQualifyingRate(Double qualifyingRate) {
        this.qualifyingRate = qualifyingRate;
    }

    @JsonProperty("loan_type")
    public String getLoanType() {
        return loanType;
    }

    @JsonProperty("loan_type")
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    @JsonProperty("loan_term")
    public Integer getLoanTerm() {
        return loanTerm;
    }

    @JsonProperty("loan_term")
    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    @JsonProperty("amortization_type")
    public String getAmortizationType() {
        return amortizationType;
    }

    @JsonProperty("amortization_type")
    public void setAmortizationType(String amortizationType) {
        this.amortizationType = amortizationType;
    }

    @JsonProperty("loan_purpose")
    public String getLoanPurpose() {
        return loanPurpose;
    }

    @JsonProperty("loan_purpose")
    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    @JsonProperty("refi_purpose")
    public String getRefiPurpose() {
        return refiPurpose;
    }

    @JsonProperty("refi_purpose")
    public void setRefiPurpose(String refiPurpose) {
        this.refiPurpose = refiPurpose;
    }

    @JsonProperty("balloon")
    public String getBalloon() {
        return balloon;
    }

    @JsonProperty("balloon")
    public void setBalloon(String balloon) {
        this.balloon = balloon;
    }

    @JsonProperty("community_lending")
    public String getCommunityLending() {
        return communityLending;
    }

    @JsonProperty("community_lending")
    public void setCommunityLending(String communityLending) {
        this.communityLending = communityLending;
    }

    @JsonProperty("payment_frequency")
    public Object getPaymentFrequency() {
        return paymentFrequency;
    }

    @JsonProperty("payment_frequency")
    public void setPaymentFrequency(Object paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    @JsonProperty("lien_type")
    public Object getLienType() {
        return lienType;
    }

    @JsonProperty("lien_type")
    public void setLienType(Object lienType) {
        this.lienType = lienType;
    }

    @JsonProperty("amt_subordinate_fin")
    public String getAmtSubordinateFin() {
        return amtSubordinateFin;
    }

    @JsonProperty("amt_subordinate_fin")
    public void setAmtSubordinateFin(String amtSubordinateFin) {
        this.amtSubordinateFin = amtSubordinateFin;
    }

    @JsonProperty("owner_existing_mtg_buydown")
    public String getOwnerExistingMtgBuydown() {
        return ownerExistingMtgBuydown;
    }

    @JsonProperty("owner_existing_mtg_buydown")
    public void setOwnerExistingMtgBuydown(String ownerExistingMtgBuydown) {
        this.ownerExistingMtgBuydown = ownerExistingMtgBuydown;
    }

    @JsonProperty("pand_i")
    public Double getPandI() {
        return pandI;
    }

    @JsonProperty("pand_i")
    public void setPandI(Double pandI) {
        this.pandI = pandI;
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
