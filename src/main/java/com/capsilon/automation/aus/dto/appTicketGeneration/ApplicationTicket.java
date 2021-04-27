package com.capsilon.automation.aus.dto.appTicketGeneration;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationTicket {
    @JsonProperty
    private String id;
    @JsonProperty
    private String userTicketId;
    @JsonProperty
    private String applicationName;
    @JsonProperty
    private String siteAddress;
    @JsonProperty
    private String sessionType;
    @JsonProperty
    private int expirySeconds;
    @JsonProperty
    private int timeInBetweenUsesInMilliSeconds;

    @JsonProperty
    public String getApplicationTicketId(){
      return id;
    }
}
