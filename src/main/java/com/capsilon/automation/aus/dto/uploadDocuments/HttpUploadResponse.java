package com.capsilon.automation.aus.dto.uploadDocuments;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "response")
public class HttpUploadResponse {

    private List<ResponseFile> responseFiles = new ArrayList<>();

    @XmlElementWrapper(name = "files")
    @XmlElement(name = "file")
    public List<ResponseFile> getResponseFiles() {
        return responseFiles;
    }
}


