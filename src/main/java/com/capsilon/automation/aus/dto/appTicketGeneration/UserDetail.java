package com.capsilon.automation.aus.dto.appTicketGeneration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetail {
    @JsonProperty
    private String id;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String emailId;
    @JsonProperty
    private String phone;
    @JsonProperty
    private String fax;
    @JsonProperty
    private String roleName;
}
