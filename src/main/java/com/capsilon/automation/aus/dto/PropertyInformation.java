
package com.capsilon.automation.aus.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "property_address",
    "property_type",
    "appraisal_waiver",
    "address_of_employer",
    "occupancy_status",
    "number_of_units"
})
public class PropertyInformation {

    @JsonProperty("property_address")
    private String propertyAddress;
    @JsonProperty("property_type")
    private String propertyType;
    @JsonProperty("appraisal_waiver")
    private Object appraisalWaiver;
    @JsonProperty("address_of_employer")
    private Object addressOfEmployer;
    @JsonProperty("occupancy_status")
    private String occupancyStatus;
    @JsonProperty("number_of_units")
    private String numberOfUnits;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("property_address")
    public String getPropertyAddress() {
        return propertyAddress;
    }

    @JsonProperty("property_address")
    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    @JsonProperty("property_type")
    public String getPropertyType() {
        return propertyType;
    }

    @JsonProperty("property_type")
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    @JsonProperty("appraisal_waiver")
    public Object getAppraisalWaiver() {
        return appraisalWaiver;
    }

    @JsonProperty("appraisal_waiver")
    public void setAppraisalWaiver(Object appraisalWaiver) {
        this.appraisalWaiver = appraisalWaiver;
    }

    @JsonProperty("address_of_employer")
    public Object getAddressOfEmployer() {
        return addressOfEmployer;
    }

    @JsonProperty("address_of_employer")
    public void setAddressOfEmployer(Object addressOfEmployer) {
        this.addressOfEmployer = addressOfEmployer;
    }

    @JsonProperty("occupancy_status")
    public String getOccupancyStatus() {
        return occupancyStatus;
    }

    @JsonProperty("occupancy_status")
    public void setOccupancyStatus(String occupancyStatus) {
        this.occupancyStatus = occupancyStatus;
    }

    @JsonProperty("number_of_units")
    public String getNumberOfUnits() {
        return numberOfUnits;
    }

    @JsonProperty("number_of_units")
    public void setNumberOfUnits(String numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
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
