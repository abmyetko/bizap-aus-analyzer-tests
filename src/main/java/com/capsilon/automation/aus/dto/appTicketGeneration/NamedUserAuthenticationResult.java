package com.capsilon.automation.aus.dto.appTicketGeneration;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NamedUserAuthenticationResult {

    @JsonProperty
    private UserTicket userTicket;
    @JsonProperty
    private boolean challenge;
    @JsonProperty
    private boolean forcedPasswordChange;
    @JsonProperty
    private boolean showPWDExpireWarning;
    @JsonProperty
    private int pwdExpireDaysLeft;
    @JsonProperty
    private int graceLoginRemaining;
    @JsonProperty
    private String cdvToken;
    @JsonProperty
    private List<String> challengeModeSupported;

    @JsonProperty
    public UserTicket getUserTicket(){
        return userTicket;
    }
}
