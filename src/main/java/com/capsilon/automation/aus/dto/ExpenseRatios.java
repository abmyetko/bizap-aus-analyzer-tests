
package com.capsilon.automation.aus.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "including_less10_mos",
    "with_undisclosed_debt"
})
public class ExpenseRatios {

    @JsonProperty("including_less10_mos")
    private Double includingLess10Mos;
    @JsonProperty("with_undisclosed_debt")
    private Double withUndisclosedDebt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("including_less10_mos")
    public Double getIncludingLess10Mos() {
        return includingLess10Mos;
    }

    @JsonProperty("including_less10_mos")
    public void setIncludingLess10Mos(Double includingLess10Mos) {
        this.includingLess10Mos = includingLess10Mos;
    }

    @JsonProperty("with_undisclosed_debt")
    public Double getWithUndisclosedDebt() {
        return withUndisclosedDebt;
    }

    @JsonProperty("with_undisclosed_debt")
    public void setWithUndisclosedDebt(Double withUndisclosedDebt) {
        this.withUndisclosedDebt = withUndisclosedDebt;
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
