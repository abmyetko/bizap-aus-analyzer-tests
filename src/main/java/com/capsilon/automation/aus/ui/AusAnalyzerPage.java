package com.capsilon.automation.aus.ui;

import com.capsilon.automation.aus.ui.component.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AusAnalyzerPage extends UIComponent {

    private static final String DATA_TESTID = "data-testid";

    public AusAnalyzerPage() {
        super($("#root"));
    }

    public List<MessageWithDocs> getMessageWithDocs() {
        return getComponents("navigation-item")
            .stream()
            .map(MessageWithDocs::new)
            .collect(Collectors.toList());
    }

    public List<MessageWithAssociatedDocs> getMessagesWithAssociatedDocs() {
        return $$(byXpath("//div[@data-testid='navigation-item'][.//*[contains(text(),'associated documents')]]"))
            .stream()
            .map(MessageWithAssociatedDocs::new)
            .collect(Collectors.toList());
    }

    public ElementsCollection getMessagesCollection() {
        return getComponents("navigation-item-message");
    }

    public List<Message> getMessages() {
        return getComponents("navigation-item-message")
            .stream()
            .map(Message::new)
            .collect(Collectors.toList());
    }

    public List<Category> getCategories() {
        return getComponents("navigation-group-expand-button")
            .stream()
            .map(Category::new)
            .collect(Collectors.toList());
    }

    public List<Message> getMessagesByCategory(String categoryName) {
        Category category = getCategories()
            .stream()
            .filter(c -> c.getElement().is(text(categoryName)))
            .findFirst()
            .orElseThrow();

        return category.getMessagesWithDocs()
            .stream()
            .map(MessageWithDocs::getMessage)
            .collect(Collectors.toList());
    }

    public List<Document> getDocuments() {
        return getDocumentsCollection()
            .stream()
            .map(Document::new)
            .collect(Collectors.toList());
    }

    public ElementsCollection getDocumentsCollection() {
        return getComponents("navigation-item-document");
    }

    public String getPopoverText(){
        return $(byXpath("//div[@id='portal']//p")).getText();
    }

    public boolean isPopoverContainTable(){
        return !$$(byXpath("//div[@id='portal']//table")).isEmpty();
    }

    public List<Document> getUnreadDocuments() {
        sleep(10000); //TODO Fix
        return getDocuments()
            .stream()
            .filter(NavigationElement::isNotReviewed)
            .collect(Collectors.toList());
    }

    public List<Message> getUnreadMessages() {
        return getMessages()
            .stream()
            .filter(NavigationElement::isNotReviewed)
            .collect(Collectors.toList());
    }

    public Message getUnreadMessage() {
        return getUnreadMessages().get(0);
    }

    public List<Message> getUnreadMessagesWithoutDocs() {
        return getMessageWithDocs()
            .stream()
            .filter(messageWithDocs -> messageWithDocs.getMessage().isNotReviewed() && messageWithDocs.getDocuments().isEmpty())
            .map(MessageWithDocs::getMessage)
            .collect(Collectors.toList());
    }

    public boolean checkFindingMarkerColor(NavigationElement messageOrDoc, FindingMarkerColor color, long timeout) {
        long pollingInterval = 50;
        SelenideElement element = messageOrDoc.getFindingMarker().getElement();
        while (timeout > 0) {
            if (element.getCssValue("background-color").contains(color.getValue())) {
                return true;
            }
            Selenide.sleep(pollingInterval);
            timeout -= pollingInterval;
        }
        return false;
    }

    public List<Filter> getFilters() {
        return $(byAttribute(DATA_TESTID, "filters"))
            .$$("span")
            .stream()
            .map(Filter::new)
            .collect(Collectors.toList());
    }

    public Filter getNotReviewedFilter() {
        return getFilters()
            .stream()
            .filter(filter -> filter.getLabel().is(matchText("Not reviewed")))
            .findFirst()
            .orElseThrow(() -> new AssertionError("'Not reviewed' filter not found!"));
    }

    public Filter getAllFilter() {
        return getFilters()
            .stream()
            .filter(filter -> filter.getLabel().is(matchText("All")))
            .findFirst()
            .orElseThrow(() -> new AssertionError("'All' filter not found!"));
    }

    public ElementsCollection getFindingMarkers() {
        return $$(byAttribute(DATA_TESTID, "finding-marker"));
    }

    public ReportsSelector getReportSelector() {
        return new ReportsSelector();
    }

    public SelenideElement getSummary() {
        return $(byText("Summary"));
    }

    public SelenideElement getRecommendation() {
        return $(byText("Recommendation")).$x(".//following::label[1]");
    }

    public int getRedBubblesCount() {
        return (int) getFindingMarkers()
            .stream()
            .filter(element -> element.getCssValue("background-color").contains(FindingMarkerColor.RED.getValue()))
            .count();
    }

    public SelenideElement getSummaryContainer() {
        return $("#summary-container");
    }
}
