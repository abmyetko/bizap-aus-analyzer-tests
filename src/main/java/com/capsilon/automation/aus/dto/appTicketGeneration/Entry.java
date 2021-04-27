package com.capsilon.automation.aus.dto.appTicketGeneration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
    @JsonProperty("entry")
    private List<KeyValue> keyValuePairs;
}
