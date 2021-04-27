
package com.capsilon.automation.aus.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "total_available_assets",
    "cash_back",
    "funds_required_to_close",
    "net_cash_back",
    "reserves_required_to_be_verified",
    "excess_available_assets_not_required_to_be_verified",
    "total_funds_to_be_verified"
})
public class Funds {

    @JsonProperty("total_available_assets")
    private Double totalAvailableAssets;
    @JsonProperty("cash_back")
    private Double cashBack;
    @JsonProperty("funds_required_to_close")
    private Double fundsRequiredToClose;
    @JsonProperty("net_cash_back")
    private Double netCashBack;
    @JsonProperty("reserves_required_to_be_verified")
    private Double reservesRequiredToBeVerified;
    @JsonProperty("excess_available_assets_not_required_to_be_verified")
    private Double excessAvailableAssetsNotRequiredToBeVerified;
    @JsonProperty("total_funds_to_be_verified")
    private Double totalFundsToBeVerified;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("total_available_assets")
    public Double getTotalAvailableAssets() {
        return totalAvailableAssets;
    }

    @JsonProperty("total_available_assets")
    public void setTotalAvailableAssets(Double totalAvailableAssets) {
        this.totalAvailableAssets = totalAvailableAssets;
    }

    @JsonProperty("cash_back")
    public Double getCashBack() {
        return cashBack;
    }

    @JsonProperty("cash_back")
    public void setCashBack(Double cashBack) {
        this.cashBack = cashBack;
    }

    @JsonProperty("funds_required_to_close")
    public Double getFundsRequiredToClose() {
        return fundsRequiredToClose;
    }

    @JsonProperty("funds_required_to_close")
    public void setFundsRequiredToClose(Double fundsRequiredToClose) {
        this.fundsRequiredToClose = fundsRequiredToClose;
    }

    @JsonProperty("net_cash_back")
    public Double getNetCashBack() {
        return netCashBack;
    }

    @JsonProperty("net_cash_back")
    public void setNetCashBack(Double netCashBack) {
        this.netCashBack = netCashBack;
    }

    @JsonProperty("reserves_required_to_be_verified")
    public Double getReservesRequiredToBeVerified() {
        return reservesRequiredToBeVerified;
    }

    @JsonProperty("reserves_required_to_be_verified")
    public void setReservesRequiredToBeVerified(Double reservesRequiredToBeVerified) {
        this.reservesRequiredToBeVerified = reservesRequiredToBeVerified;
    }

    @JsonProperty("excess_available_assets_not_required_to_be_verified")
    public Double getExcessAvailableAssetsNotRequiredToBeVerified() {
        return excessAvailableAssetsNotRequiredToBeVerified;
    }

    @JsonProperty("excess_available_assets_not_required_to_be_verified")
    public void setExcessAvailableAssetsNotRequiredToBeVerified(Double excessAvailableAssetsNotRequiredToBeVerified) {
        this.excessAvailableAssetsNotRequiredToBeVerified = excessAvailableAssetsNotRequiredToBeVerified;
    }

    @JsonProperty("total_funds_to_be_verified")
    public Double getTotalFundsToBeVerified() {
        return totalFundsToBeVerified;
    }

    @JsonProperty("total_funds_to_be_verified")
    public void setTotalFundsToBeVerified(Double totalFundsToBeVerified) {
        this.totalFundsToBeVerified = totalFundsToBeVerified;
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
