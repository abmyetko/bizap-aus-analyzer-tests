package com.capsilon.automation.aus.ui;

public enum FindingMarkerColor {

    RED("252, 61, 89"),
    BLUE("11, 108, 224"),
    GRAY("132, 143, 155"),
    GREEN("91, 216, 135");

    private final String value;

    FindingMarkerColor(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
