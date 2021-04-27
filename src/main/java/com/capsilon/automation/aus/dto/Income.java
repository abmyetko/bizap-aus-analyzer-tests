
package com.capsilon.automation.aus.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "base",
    "commission",
    "bonus",
    "overtime",
    "other",
    "positive_net_rental",
    "subj_pos_cash_flow",
    "total"
})
public class Income {

    @JsonProperty("base")
    private Double base;
    @JsonProperty("commission")
    private Double commission;
    @JsonProperty("bonus")
    private Double bonus;
    @JsonProperty("overtime")
    private Double overtime;
    @JsonProperty("other")
    private Double other;
    @JsonProperty("positive_net_rental")
    private Double positiveNetRental;
    @JsonProperty("subj_pos_cash_flow")
    private Double subjPosCashFlow;
    @JsonProperty("total")
    private Double total;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("base")
    public Double getBase() {
        return base;
    }

    @JsonProperty("base")
    public void setBase(Double base) {
        this.base = base;
    }

    @JsonProperty("commission")
    public Double getCommission() {
        return commission;
    }

    @JsonProperty("commission")
    public void setCommission(Double commission) {
        this.commission = commission;
    }

    @JsonProperty("bonus")
    public Double getBonus() {
        return bonus;
    }

    @JsonProperty("bonus")
    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    @JsonProperty("overtime")
    public Double getOvertime() {
        return overtime;
    }

    @JsonProperty("overtime")
    public void setOvertime(Double overtime) {
        this.overtime = overtime;
    }

    @JsonProperty("other")
    public Double getOther() {
        return other;
    }

    @JsonProperty("other")
    public void setOther(Double other) {
        this.other = other;
    }

    @JsonProperty("positive_net_rental")
    public Double getPositiveNetRental() {
        return positiveNetRental;
    }

    @JsonProperty("positive_net_rental")
    public void setPositiveNetRental(Double positiveNetRental) {
        this.positiveNetRental = positiveNetRental;
    }

    @JsonProperty("subj_pos_cash_flow")
    public Double getSubjPosCashFlow() {
        return subjPosCashFlow;
    }

    @JsonProperty("subj_pos_cash_flow")
    public void setSubjPosCashFlow(Double subjPosCashFlow) {
        this.subjPosCashFlow = subjPosCashFlow;
    }

    @JsonProperty("total")
    public Double getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Double total) {
        this.total = total;
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
