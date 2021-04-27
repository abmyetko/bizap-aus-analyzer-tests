package com.capsilon.automation.aus.dto.fileddocs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FiledDocs {

    @JsonProperty("Document")
    private List<FiledDoc> document;
}
