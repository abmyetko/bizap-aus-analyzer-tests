package com.capsilon.automation.aus.ui.component;

import com.capsilon.automation.aus.ui.FindingMarkerColor;
import com.codeborne.selenide.SelenideElement;

public class FindingMarker extends UIComponent {

    protected FindingMarker(SelenideElement container) {
        super(container);
    }

    public FindingMarkerColor getColor() {
        if (isColor(FindingMarkerColor.RED)) {
            return FindingMarkerColor.RED;
        } else if (isColor(FindingMarkerColor.GRAY)) {
            return FindingMarkerColor.GRAY;
        } else if (isColor(FindingMarkerColor.GREEN)) {
            return FindingMarkerColor.GREEN;
        } else if (isColor(FindingMarkerColor.BLUE)) {
            return FindingMarkerColor.BLUE;
        } else {
            throw new Error("FindingMarker background-color undefined, value: " + container.getCssValue("background-color"));
        }
    }

    private boolean isColor(FindingMarkerColor color) {
        return container.getCssValue("background-color").contains(color.getValue());
    }
}
