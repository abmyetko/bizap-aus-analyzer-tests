package com.capsilon.automation.aus.ui.component;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.sleep;

public class Document extends NavigationElement {
    public Document(SelenideElement container) {
        super(container);
    }

    public String getName() {
        return container.getText();
    }

    public String getDocumentStatus() {
        return container.$x(".//label/span").pseudo(":before").replace("\"", "");
    }

    public void changeReviewedStatus() {
        container.$x(".//label/span").click();
        sleep(30000); // TODO wait echo fix
    }

    public boolean isReviewButtonPresent() {
        return container.$$x(".//label/span").size()>0;

    }
}
