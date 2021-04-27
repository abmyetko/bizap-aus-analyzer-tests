package com.capsilon.automation.aus.ui.component;

import com.codeborne.selenide.SelenideElement;

public class Message extends NavigationElement {
    public Message(SelenideElement container) {
        super(container);
    }

    public SelenideElement hover() {
        container.scrollIntoView(false);
        return container.hover();
    }

    public String getText() {
        return container.getText();
    }
}
