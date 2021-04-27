package com.capsilon.automation.aus.dto.appTicketGeneration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "responseId",
        "domainId",
        "brandName",
        "domainAddress",
        "productId",
        "productName",
        "siteId",
        "siteName",
        "siteStatus",
        "urls",
        "metaInfo",
        "orchestrationApplication"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidateApplicationAndSiteResult {
    @JsonProperty
    private String responseId;
    @JsonProperty
    private String domainId;
    @JsonProperty
    private String brandName;
    @JsonProperty
    private String domainAddress;
    @JsonProperty
    private String productId;
    @JsonProperty
    private String productName;
    @JsonProperty
    private String siteId;
    @JsonProperty
    private String siteName;
    @JsonProperty
    private String siteStatus;
    @JsonProperty
    private List<Url> urls;
    @JsonProperty
    private Entry metaInfo;
    @JsonProperty
    private boolean orchestrationApplication;

    @JsonProperty
    public String getSiteId(){
        return siteId;
    }
    @JsonProperty
    public String getResponseId(){
        return responseId;
    }

    @JsonProperty
    public String getUrlByKey(String key){
        String urlByKey = "";
        for (Url url:urls){
            if(url.getKey().contains(key)){
                urlByKey = url.getValue();
            }
        }
        return urlByKey;
    }


//
//    public Url getUrlByKey(String key) throws NoItemFoundException {
//        return urls.stream()
//                .filter(c -> key.equals(c.getKey()))
//                .findFirst()
//                .orElseThrow(() -> new NoItemFoundException("Cannot find url by key " + key));
//    }
}
