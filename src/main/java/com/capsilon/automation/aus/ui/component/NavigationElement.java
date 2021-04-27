package com.capsilon.automation.aus.ui.component;

import com.capsilon.automation.aus.ui.FindingMarkerColor;
import com.codeborne.selenide.SelenideElement;

public abstract class NavigationElement extends UIComponent {

    protected NavigationElement(SelenideElement container) {
        super(container);
    }

    public FindingMarker getFindingMarker() {
        SelenideElement component = getComponent("finding-marker");
        return new FindingMarker(component);
    }

    public boolean isFindingMarkerPresent() {
        return !(getComponents("finding-marker").isEmpty());

    }

    public boolean isNotReviewed() {
        return FindingMarkerColor.RED.equals(getFindingMarker().getColor());
    }
}
