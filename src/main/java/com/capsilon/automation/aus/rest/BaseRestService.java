package com.capsilon.automation.aus.rest;

import com.capsilon.automation.bam.rest.admin.BizAppDataProvider;
import com.capsilon.automation.bam.rest.admin.BizAppHttpStatusCodes;
import lombok.SneakyThrows;
import org.json.simple.parser.ParseException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class BaseRestService extends BizAppDataProvider {

    protected static ObjectMapper objectMapper = new ObjectMapper();
    protected static Map<String, String> cookies = new HashMap<>();
    protected static Map<String, String> folderIdsMap = new HashMap<>();

    public Map<String, String>  getCookies() throws ParseException {
        if (headersMap.isEmpty()) {
            prepareData();
            return getCookiesMap();
        } else {
            cookies.put("x-session-token", headersMap.get("x-session-token"));
            cookies.put("XSRF-TOKEN", headersMap.get("X-XSRF-TOKEN"));
            return cookies;
        }
    }

    public String getFolderIdLong(String folderId) throws ParseException {
        if (folderIdsMap.containsKey(folderId)) {
            return folderIdsMap.get(folderId);
        } else {
            return getDvFolderIdLong(folderId);
        }
    }

    @SneakyThrows
    public String toPrettyJson(Object object) {
        Object obj = (object instanceof String) ? objectMapper.readValue((String) object, Object.class) : object;
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    @SneakyThrows
    public void deleteSession() {
        String service = "/gateway/api/dmsSession";
        given()
            .contentType(CONTENT_TYPE)
            .headers(headersMap)
            .when()
            .delete(API_ROOT + service)
            .then()
            .statusCode(BizAppHttpStatusCodes.OK.getCode());

        headersMap = new HashMap<>();
        cookies = new HashMap<>();
    }

    @SneakyThrows
    public String createNewFolder() {
        String service = "/flams/api/v3/pipelineFolders";
        File fnmFile = new File(getClass().getResource("/loan.fnm").getFile());
        Map<String, String> cookies = getCookies();
        return createNewLoanFolder(
            headersMap,
            cookies,
            service,
            fnmFile
        );
    }

}
