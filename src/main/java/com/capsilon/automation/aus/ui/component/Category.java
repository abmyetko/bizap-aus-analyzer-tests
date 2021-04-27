package com.capsilon.automation.aus.ui.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.sleep;

public class Category extends UIComponent {
    public Category(SelenideElement container) {
        super(container);
    }

    public SelenideElement getExpandList() {
        return container.$x("./following::div[@data-testid='navigation-group-expand-list']");
    }

    public List<MessageWithDocs> getMessagesWithDocs() {
        return getExpandList()
            .$$(byAttribute(DATA_TESTID, "navigation-item"))
            .stream()
            .map(MessageWithDocs::new)
            .collect(Collectors.toList());
    }

    public List<MessageWithAssociatedDocs> getMessagesWithAssociatedDocs() {
        return getExpandList()
            .$$(byXpath("//div[@data-testid='navigation-item'][.//*[contains(text(),'associated documents')]]"))
            .stream()
            .map(MessageWithAssociatedDocs::new)
            .collect(Collectors.toList());
    }

    public boolean isCollapsed() {
        return getExpandList().is(Condition.attribute("height", "0"));
    }

    public String getName() {
        return container.getText();
    }

    public void click() {
        container.click();
        sleep(200);
    }
}
