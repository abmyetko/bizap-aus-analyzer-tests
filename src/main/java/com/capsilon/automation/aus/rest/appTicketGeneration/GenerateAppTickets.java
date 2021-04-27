package com.capsilon.automation.aus.rest.appTicketGeneration;

import com.capsilon.automation.aus.dto.appTicketGeneration.ApplicationTicket;
import com.capsilon.automation.aus.dto.appTicketGeneration.NamedUserAuthenticationResult;
import com.capsilon.automation.aus.dto.appTicketGeneration.ValidateApplicationAndSiteResult;

import java.util.ArrayList;
import java.util.List;

public class GenerateAppTickets {

    private static final ValidateApplicationAndSiteService validateApplicationAndSiteService = new ValidateApplicationAndSiteService();
    private static final AuthenticateService AUTHENTICATE_SERVICE = new AuthenticateService();
    private static final ApplicationTicketService applicationTicketService = new ApplicationTicketService();

    private static List<ApplicationTicket> applicationTickets = new ArrayList<>();

    public List<ApplicationTicket> getApplicationTicketsList(int numberOfTickets) {
        ValidateApplicationAndSiteResult validateApplicationAndSiteResult = validateApplicationAndSiteService.validateApplicationAndSite();
        NamedUserAuthenticationResult namedUserAuthenticationResult = AUTHENTICATE_SERVICE.authenticate(validateApplicationAndSiteResult);
        return applicationTickets = applicationTicketService
            .generateApplicationTickets(
                String.valueOf(numberOfTickets),
                namedUserAuthenticationResult.getUserTicket().getUserTicketId(),
                validateApplicationAndSiteResult)
            .getApplicationTicketsList();
    }

    public String getApplicationTicketId() {
        if (applicationTickets.isEmpty()) {
            applicationTickets = getApplicationTicketsList(2);
        }
        return applicationTickets.get(applicationTickets.size() - 1).getApplicationTicketId();
    }
}
