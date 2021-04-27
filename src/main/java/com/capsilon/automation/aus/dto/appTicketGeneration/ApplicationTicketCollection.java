package com.capsilon.automation.aus.dto.appTicketGeneration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationTicketCollection {
    @JsonProperty
    private List<ApplicationTicket> applicationTickets;

    @JsonProperty
    public List<ApplicationTicket> getApplicationTicketsList(){
        return applicationTickets;
    }
}
