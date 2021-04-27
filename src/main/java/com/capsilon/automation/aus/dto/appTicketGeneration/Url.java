package com.capsilon.automation.aus.dto.appTicketGeneration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Url {
    @JsonProperty
    private String key;
    @JsonProperty
    private String value;

    @JsonProperty
    public String getKey(){
        return key;
    }
    @JsonProperty
    public String getValue(){
        return value;
    }
}
