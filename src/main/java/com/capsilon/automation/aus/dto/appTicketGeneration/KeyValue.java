package com.capsilon.automation.aus.dto.appTicketGeneration;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class KeyValue {
    private String key;
    private String value;

    public static KeyValue ofEntry(Map.Entry<String, String> entry) {
        return of(entry.getKey(), entry.getValue());
    }
}
