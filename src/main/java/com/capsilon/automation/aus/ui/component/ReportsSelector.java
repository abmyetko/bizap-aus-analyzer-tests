package com.capsilon.automation.aus.ui.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReportsSelector extends UIComponent {

    public ReportsSelector() {
        super($(byAttribute(DATA_TESTID, "reports-selector")));
    }

    public void expand() {
        if (getMenu().is(not(Condition.visible))) {
            getElement().click();
            getMenu().shouldBe(visible);
        }
    }

    public void collapse() {
        if (getMenu().is(Condition.visible)) {
            getElement().click();
            getMenu().shouldNotBe(visible);
        }
    }

    public ElementsCollection getOptions() {
        return getElement().$$(byClassName("report-selector__option"));
    }

    public SelenideElement getMenu() {
        return getElement().$(byClassName("report-selector__menu"));
    }

    public void optionShouldBeSelected(int index) {
        getOptions()
            .get(index)
            .shouldHave(cssClass("report-selector__option--is-selected"))
            .shouldBe(visible)
            .$(".report-selector__option-checkmark")
            .shouldBe(visible);
    }

    public void optionShouldBeFocused(int index) {
        getOptions()
            .get(index)
            .shouldHave(cssClass("report-selector__option--is-focused"))
            .shouldBe(visible);
    }

    public List<Date> getReportsDates() {
        expand();
        List<Date> dates = new ArrayList<>();
        $$(".report-selector__option-sub-label")
            .texts()
            .forEach(text -> dates.add(formatDate(text)));

        collapse();
        return dates;
    }

    public List<SelenideElement> getReadOnlyReports() {
        expand();
        return getOptions()
            .stream()
            .filter(element -> element.getText().contains("Read Only"))
            .collect(Collectors.toList());
    }

    public void chooseReadOnlyReport() {
        List<SelenideElement> reports = getReadOnlyReports();
        reports.get(0).click();
    }

    public boolean isCurrentReportReadOnly() {
        return $(".report-selector__single-value-sub-label")
            .getText().contains("Read Only");
    }

    @SneakyThrows
    private Date formatDate(String date) {
        String sFormattedDate = date.replace("\u0000", "").trim();
        return new SimpleDateFormat("MM/d/yyyy hh:mm a", Locale.US).parse(sFormattedDate);
    }
}
