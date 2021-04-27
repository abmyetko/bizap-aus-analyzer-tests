package com.capsilon.automation.aus.wrappers;

import com.capsilon.automation.aus.dto.appTicketGeneration.NamedUserAuthenticationResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NamedUserAuthenticationResultWrapper {

    private NamedUserAuthenticationResult namedUserAuthenticationResult;
}
