package com.capsilon.automation.aus.ui.component;

import cn.com.jautoitx.util.ControlIdBuilder;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;

public class MessageWithAssociatedDocs extends UIComponent {
    public MessageWithAssociatedDocs(SelenideElement container) {
        super(container);
    }

    public Message getMessage() {
        SelenideElement component = getComponent("navigation-item-message");
        return new Message(component);
    }

    public List<Document> getDocuments() {
        ElementsCollection components = getComponents("navigation-item-document");
        return components
            .stream()
            .map(Document::new)
            .collect(Collectors.toList());
    }

    public void expandAsscociatedDocs(){
        SelenideElement hideExpandButton = container.$(By.xpath(".//*[contains(text(),'associated documents')]"));
        if (hideExpandButton.getText().contains("Show")){
            hideExpandButton.click();
        }
    }

    public void hideAsscociatedDocs(){
        SelenideElement hideExpandButton = container.$(By.xpath("//*[contains(text(),'associated documents')]"));
        if (hideExpandButton.getText().contains("Hide")){
            hideExpandButton.click();
        }
    }
}
