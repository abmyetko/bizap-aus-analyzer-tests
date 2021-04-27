package com.capsilon.automation.aus.dto.appTicketGeneration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTicket {
    @JsonProperty
    private String id;
    @JsonProperty
    private String siteId;
    @JsonProperty
    private String productId;
    @JsonProperty
    private String siteAddress;
    @JsonProperty
    private UserDetail userDetail;
    @JsonProperty
    private String sessionType;
    @JsonProperty
    private List<String> privileges;
    @JsonProperty
    private String accountType;
    @JsonProperty
    private Date lastLoginTime;
    @JsonProperty
    private boolean globalAccess;
    @JsonProperty
    private Date createTime;
    @JsonProperty
    private int expirySeconds;
    @JsonProperty
    private boolean readOnlyAccess;
    @JsonProperty
    private String applicationName;
    @JsonProperty
    private String documentFilterType;
    @JsonProperty
    private List<String> documentTypeList;
    @JsonProperty
    private boolean allWorkspaceAppletAllowed;
    @JsonProperty
    private List<String> allowedWorkspaceAppletList;
    @JsonProperty
    private boolean enableWorkspaceNavigation;
    @JsonProperty
    private boolean allConditionsAllowed;
    @JsonProperty
    private boolean allConditionStatusesAllowed;
    @JsonProperty
    private List<String> allowedConditionStatus;
    @JsonProperty
    private boolean allConditionStagesAllowed;
    @JsonProperty
    private List<String> allowedConditionStages;
    @JsonProperty
    private boolean internalConditionsAllowed;
    @JsonProperty
    private boolean allRoleCategoriesAllowed;
    @JsonProperty
    private List<String> allowedRoleCategories;
    @JsonProperty
    private String userType;
    @JsonProperty
    private String partnerCompanyId;

    @JsonProperty
    public String getUserTicketId(){
        return id;
    }
}
