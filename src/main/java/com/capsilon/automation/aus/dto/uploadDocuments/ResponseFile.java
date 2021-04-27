package com.capsilon.automation.aus.dto.uploadDocuments;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class ResponseFile {

    @XmlAttribute
    private String id;
    @XmlElement
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}