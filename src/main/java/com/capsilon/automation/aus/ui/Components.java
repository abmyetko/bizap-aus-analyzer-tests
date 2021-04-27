package com.capsilon.automation.aus.ui;

public enum Components {

    REPORTS_SELECTOR("reports-selector"),
    NAVIGATOR_CONTAINER("navigation-container"),
    NAVIGATION_ITEM("navigation-item");

    private final String dataTestId;

    Components(String dataTestId) {
        this.dataTestId = dataTestId;
    }

    public String getDataTestId() {
        return dataTestId;
    }
}
