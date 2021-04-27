
package com.capsilon.automation.aus.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "first_pand_iqualifying",
    "negative_net_rental",
    "second_pand_i",
    "subj_neg_cash_flow",
    "hazard_insurance",
    "all_other_payments",
    "taxes",
    "total_expense_payment",
    "mortgage_insurance",
    "hoa_fees",
    "present_principal_housing_payment",
    "other",
    "total_housing_payment"
})
public class ProposedMonthlyPayment {

    @JsonProperty("first_pand_iqualifying")
    private Double firstPandIqualifying;
    @JsonProperty("negative_net_rental")
    private Double negativeNetRental;
    @JsonProperty("second_pand_i")
    private Double secondPandI;
    @JsonProperty("subj_neg_cash_flow")
    private Double subjNegCashFlow;
    @JsonProperty("hazard_insurance")
    private Double hazardInsurance;
    @JsonProperty("all_other_payments")
    private Double allOtherPayments;
    @JsonProperty("taxes")
    private Double taxes;
    @JsonProperty("total_expense_payment")
    private Double totalExpensePayment;
    @JsonProperty("mortgage_insurance")
    private Double mortgageInsurance;
    @JsonProperty("hoa_fees")
    private Double hoaFees;
    @JsonProperty("present_principal_housing_payment")
    private Double presentPrincipalHousingPayment;
    @JsonProperty("other")
    private Double other;
    @JsonProperty("total_housing_payment")
    private Double totalHousingPayment;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("first_pand_iqualifying")
    public Double getFirstPandIqualifying() {
        return firstPandIqualifying;
    }

    @JsonProperty("first_pand_iqualifying")
    public void setFirstPandIqualifying(Double firstPandIqualifying) {
        this.firstPandIqualifying = firstPandIqualifying;
    }

    @JsonProperty("negative_net_rental")
    public Double getNegativeNetRental() {
        return negativeNetRental;
    }

    @JsonProperty("negative_net_rental")
    public void setNegativeNetRental(Double negativeNetRental) {
        this.negativeNetRental = negativeNetRental;
    }

    @JsonProperty("second_pand_i")
    public Double getSecondPandI() {
        return secondPandI;
    }

    @JsonProperty("second_pand_i")
    public void setSecondPandI(Double secondPandI) {
        this.secondPandI = secondPandI;
    }

    @JsonProperty("subj_neg_cash_flow")
    public Double getSubjNegCashFlow() {
        return subjNegCashFlow;
    }

    @JsonProperty("subj_neg_cash_flow")
    public void setSubjNegCashFlow(Double subjNegCashFlow) {
        this.subjNegCashFlow = subjNegCashFlow;
    }

    @JsonProperty("hazard_insurance")
    public Double getHazardInsurance() {
        return hazardInsurance;
    }

    @JsonProperty("hazard_insurance")
    public void setHazardInsurance(Double hazardInsurance) {
        this.hazardInsurance = hazardInsurance;
    }

    @JsonProperty("all_other_payments")
    public Double getAllOtherPayments() {
        return allOtherPayments;
    }

    @JsonProperty("all_other_payments")
    public void setAllOtherPayments(Double allOtherPayments) {
        this.allOtherPayments = allOtherPayments;
    }

    @JsonProperty("taxes")
    public Double getTaxes() {
        return taxes;
    }

    @JsonProperty("taxes")
    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    @JsonProperty("total_expense_payment")
    public Double getTotalExpensePayment() {
        return totalExpensePayment;
    }

    @JsonProperty("total_expense_payment")
    public void setTotalExpensePayment(Double totalExpensePayment) {
        this.totalExpensePayment = totalExpensePayment;
    }

    @JsonProperty("mortgage_insurance")
    public Double getMortgageInsurance() {
        return mortgageInsurance;
    }

    @JsonProperty("mortgage_insurance")
    public void setMortgageInsurance(Double mortgageInsurance) {
        this.mortgageInsurance = mortgageInsurance;
    }

    @JsonProperty("hoa_fees")
    public Double getHoaFees() {
        return hoaFees;
    }

    @JsonProperty("hoa_fees")
    public void setHoaFees(Double hoaFees) {
        this.hoaFees = hoaFees;
    }

    @JsonProperty("present_principal_housing_payment")
    public Double getPresentPrincipalHousingPayment() {
        return presentPrincipalHousingPayment;
    }

    @JsonProperty("present_principal_housing_payment")
    public void setPresentPrincipalHousingPayment(Double presentPrincipalHousingPayment) {
        this.presentPrincipalHousingPayment = presentPrincipalHousingPayment;
    }

    @JsonProperty("other")
    public Double getOther() {
        return other;
    }

    @JsonProperty("other")
    public void setOther(Double other) {
        this.other = other;
    }

    @JsonProperty("total_housing_payment")
    public Double getTotalHousingPayment() {
        return totalHousingPayment;
    }

    @JsonProperty("total_housing_payment")
    public void setTotalHousingPayment(Double totalHousingPayment) {
        this.totalHousingPayment = totalHousingPayment;
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
