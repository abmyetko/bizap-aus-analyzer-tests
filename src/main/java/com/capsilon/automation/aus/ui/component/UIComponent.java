package com.capsilon.automation.aus.ui.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;

public abstract class UIComponent {

    protected static final String DATA_TESTID = "data-testid";
    protected final SelenideElement container;

    protected UIComponent(SelenideElement container) {
        this.container = container;
    }

    public SelenideElement getElement() {
        return container;
    }

    protected SelenideElement getComponent(String dataTestId) {
        return container.$(byAttribute(DATA_TESTID, dataTestId));
    }

    protected ElementsCollection getComponents(String dataTestId) {
        return container.$$(byAttribute(DATA_TESTID, dataTestId));
    }
}
