package com.capsilon.automation.aus.ui.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

public class MessageWithDocs extends UIComponent {
    public MessageWithDocs(SelenideElement container) {
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
                .map(selenideElement -> new Document(selenideElement))
//            .map(Document::new)
            .collect(Collectors.toList());
    }
}
