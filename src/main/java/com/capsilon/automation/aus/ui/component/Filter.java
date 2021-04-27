package com.capsilon.automation.aus.ui.component;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;

public class Filter extends UIComponent {

    public Filter(SelenideElement container) {
        super(container);
    }

    public boolean isSelected() {
        return container.is(cssValue("border-width", "5px"));
    }

    public void select() {
        container.click();
    }

    public SelenideElement getLabel() {
        return container.$x("parent::label");
    }
}
