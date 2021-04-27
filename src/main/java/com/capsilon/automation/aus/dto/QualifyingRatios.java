
package com.capsilon.automation.aus.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "housing_expense",
    "debt_to_income"
})
public class QualifyingRatios {

    @JsonProperty("housing_expense")
    private Double housingExpense;
    @JsonProperty("debt_to_income")
    private Double debtToIncome;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("housing_expense")
    public Double getHousingExpense() {
        return housingExpense;
    }

    @JsonProperty("housing_expense")
    public void setHousingExpense(Double housingExpense) {
        this.housingExpense = housingExpense;
    }

    @JsonProperty("debt_to_income")
    public Double getDebtToIncome() {
        return debtToIncome;
    }

    @JsonProperty("debt_to_income")
    public void setDebtToIncome(Double debtToIncome) {
        this.debtToIncome = debtToIncome;
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
