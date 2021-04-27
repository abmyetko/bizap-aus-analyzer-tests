package com.capsilon.automation.aus.ui;

import com.capsilon.automation.aus.dto.Finding;
import com.capsilon.automation.aus.ui.base.TestBase;
import com.capsilon.automation.aus.ui.component.Category;
import com.capsilon.automation.aus.ui.component.Filter;
import com.capsilon.automation.aus.ui.component.Message;
import com.capsilon.automation.aus.utils.Actions;
import com.capsilon.automation.aus.utils.ReportUtils;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.collect.ArrayListMultimap;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVRecord;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static com.capsilon.automation.aus.utils.CSVUtils.getDuMappingRecords;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AusUiTest extends TestBase {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AusUiTest.class);


    @Test /*AUS-259 Make DU Findings Summary page default for AUS Analyzer*/
    @Order(1)
    public void summaryPageShouldBeDefault() {
        ausPage.getSummaryContainer().shouldBe(visible);
    }

    @Test /*AUS-295 Make "Not reviewed" filter - default*/
    @Order(1)
    public void notReviewedFilterShouldBeDefault() {
        Filter notReviewedFilter = ausPage.getNotReviewedFilter();
        Assertions.assertTrue(notReviewedFilter.isSelected(), "Not Reviewed filter not selected");
    }

    @Test/*AUS-26*/
    @Order(1)
    public void recommendationShouldBeCorrect() {
        String recommendation = ReportUtils.getRecommendation(report);
        ausPage.getRecommendation().shouldHave(text(recommendation));
    }

    @Disabled // FIXME
    @SneakyThrows
    @Test /*AUS-29 Autohide standard DU messages*/
    public void autoHideStandardDUMessages() {
        for (Finding finding : ReportUtils.getFindings(report)) {
            if (finding.getVisible() && !finding.getRead()) {
                ausPage
                    .getMessagesCollection()
                    .filter(text(finding.getMessage().trim()))
                    .shouldHave(size(1))
                    .first()
                    .shouldBe(visible);
            } else {
                ausPage
                    .getMessagesCollection()
                    .filter(text(finding.getMessage().trim()))
                    .shouldHave(size(0));
            }
        }
    }

    @Disabled // FIXME
    @Test/*AUS-43*/
    public void allNotHidedMessagesShouldBeVisible() {
        int allImportantItemsCount = ReportUtils.getAllImportantItemsCount(report);
        ausPage.getMessagesCollection().shouldHave(size(allImportantItemsCount));
    }

    @Test/*AUS-88*/
    public void firstReportShouldBeSelectedAndFocused() {
        uploadDuReport();
        reloadAUSApp();

        ausPage.getReportSelector().expand();
        ausPage.getReportSelector().getMenu().shouldBe(visible);

        int first = 0;
        ausPage.getReportSelector().optionShouldBeSelected(first);
        ausPage.getReportSelector().optionShouldBeFocused(first);
        ausPage.getReportSelector().collapse();
    }

    @Test/*AUS-88*/
    public void reportListShouldBeSorted() {
        uploadDuReport();
        reloadAUSApp();

        ausPage.getReportSelector().expand();
        ausPage.getReportSelector().getMenu().shouldBe(visible);

        List<Date> reportsDates = ausPage.getReportSelector().getReportsDates();
        Assertions.assertTrue(reportsDates.size() > 1, "Reports count should be > 1");
        Date prevDate = null;
        for (Date date : reportsDates) {
            if (prevDate == null) {
                prevDate = date;
            } else {
                if (!date.equals(prevDate)) { // date can be equal in minutes
                    assertTrue("Report dates are not sorted " + prevDate + " shouldBe before" + date,
                        date.before(prevDate));
                }
                prevDate = date;
            }
        }

        ausPage.getReportSelector().collapse();
    }

    @Test/*AUS-88*/
    public void accessTheListOfAllRequestedReports() {
        ausPage.getReportSelector().expand();
        ausPage.getReportSelector().getMenu().shouldBe(visible);

        int size = folderWithReports.getDuAllReportInfos().size();
        ausPage.getReportSelector().getOptions().shouldHave(size(size));
        ausPage.getReportSelector().collapse();
    }

    @Test/*AUS-41*/
    public void categoriesShouldBeInCategoriesList() {
        SoftAssertions softAssert = new SoftAssertions();
        String categoryName;
        HashSet<String> duCategories = new HashSet<>();
        for (CSVRecord record : getDuMappingRecords()) {
            duCategories.add(record.get("Category Name").toUpperCase());
        }
        List<Category> categories = ausPage.getCategories();
        for (Category category : categories) {
            categoryName = category.getName();
            softAssert.assertThat(categoryName)
                .as("\"" + categoryName + "\" category isn't consist in DU Categories list")
                .isIn(duCategories);
        }
        softAssert.assertAll();
    }

    @Test/*AUS-41*/
    public void duMessagesShouldBeGroupedInCorrectCategories() {
        SoftAssertions softAssert = new SoftAssertions();
        String categoryName;
        ArrayListMultimap<String, String> messageAndCategoryPairs = ArrayListMultimap.create();
        for (CSVRecord record : getDuMappingRecords()) {
            messageAndCategoryPairs.put(record.get("Category Name"), record.get("Lender Text"));
        }
        List<Category> categories = ausPage.getCategories();
        for (Category category : categories) {
            categoryName = Actions.toUpperCaseForFirstLetter(category.getName().toLowerCase());
            ArrayList<String> duMessagesByCategory = new ArrayList<>();
            for (Message message : ausPage.getMessagesByCategory(categoryName)) {
                duMessagesByCategory.add(message.getElement().getText());
            }
            for (String duMessage : duMessagesByCategory) {
                softAssert.assertThat(isMessageWithParameterCorrespondsToDuMessage(duMessage, messageAndCategoryPairs.get(categoryName)))
                    .as("DU message \"" + duMessage + "\" is included in incorrect category \"" + categoryName + "\"")
                    .isEqualTo(true);
            }
        }
        softAssert.assertAll();
    }

    @Test/*AUS-41*/
    public void categoriesShouldCollapseAndExpand() {
        SoftAssertions softAssert = new SoftAssertions();

        ausPage.getAllFilter().select();
        //TODO add new category logic for All filter: Hide/show informational, etc

        ausPage.getNotReviewedFilter().select();

        for (Category category : ausPage.getCategories()) {
            String categoryName = category.getName();
            softAssert
                .assertThat(category.isCollapsed())
                .as("Category \"" + categoryName + "\" wasn't be expanded by default")
                .isEqualTo(false);
            category.click();
            softAssert
                .assertThat(category.isCollapsed())
                .as("Category \"" + categoryName + "\" wasn't be collapsed after click")
                .isEqualTo(true);
            category.click();
            softAssert
                .assertThat(category.isCollapsed())
                .as("Category \"" + categoryName + "\" wasn't be expanded after click")
                .isEqualTo(false);
        }
        softAssert.assertAll();
    }


    @Test/*AUS-42*/
    public void markAsReadMessagesTest() {
        ausPage.getAllFilter().select();
        Message unreadMessage = ausPage.getUnreadMessage();

        assertEquals("Unread message doesn't have Red finding marker",
            FindingMarkerColor.RED, unreadMessage.getFindingMarker().getColor());

        unreadMessage.hover();

        assertTrue("Reviewed message doesn't have Gray finding marker",
            ausPage.checkFindingMarkerColor(unreadMessage, FindingMarkerColor.GRAY, REVIEW_TIMEOUT));
    }

    @Test/*AUS-42*/
    public void hideReviewedMessageTest() {
        ausPage.getNotReviewedFilter().select();

        Message unreadMessage = ausPage.getUnreadMessagesWithoutDocs().get(0);
        String messageText = unreadMessage.getText();
        int messagesCount = ausPage.getMessagesCollection().size();

        unreadMessage.hover();

        assertTrue("Message shouldn't be read",
            ausPage.checkFindingMarkerColor(unreadMessage, FindingMarkerColor.GRAY, REVIEW_TIMEOUT));
        assertEquals("Messages count in Not Reviewed filter changes immediately after reading message and before refreshing filters",
            messagesCount, ausPage.getMessagesCollection().size());
        assertTrue("Messages list in Not Reviewed doesn't contain message immediately after reading message and before refreshing filters",
            ausPage.getMessagesCollection().contains(unreadMessage.getElement()));

        ausPage.getAllFilter().select();
        ausPage.getNotReviewedFilter().select();

        assertEquals("Messages count in Not Reviewed filter changes immediately after reading message and refreshing",
            messagesCount - 1, ausPage.getMessagesCollection().size());
        ausPage
            .getMessagesCollection()
            .shouldHave(
                CollectionCondition
                    .noneMatch(
                        "Messages list in Not Reviewed filter contains reviewed message after reading message and refreshing",
                        element -> messageText.equals(element.getText()))
            );
    }

    @Test/*AUS-40*/
    public void messageWithTablePopoverTest() {
        ausPage.getAllFilter().select();
        List<Message> messagesList = ausPage.getMessages();
        Message messageWithTable = null;
        boolean folderContainsMessageWithTable = false;
        List<String> duMessagesListWithTable = new ArrayList<>();
        for (CSVRecord record : getDuMappingRecords()) {
            String text = record.get("Lender Text");
            if (text.contains("|")) {
                duMessagesListWithTable.add(text.split(System.getProperty("line.separator"))[0]);
            }
        }

        for (Message message : messagesList) {
            if (duMessagesListWithTable.contains(message.getText())) {
                messageWithTable = message;
                folderContainsMessageWithTable = true;
                break;
            }
        }

        if (folderContainsMessageWithTable) {
            String messageWithTableFullText = messageWithTable.getText();
            messageWithTable.hover();
            sleep(POPOVER_TIME);
            assertEquals("Popover doesn't contain correct text", messageWithTableFullText, ausPage.getPopoverText());
            assertTrue("Popover of message with structured information doesn't contain table", ausPage.isPopoverContainTable());
        } else {
            fail("Folder doesn't contain message with table (" + WebDriverRunner.url() + ")");
        }
    }


    @Test/*AUS-40*/
    public void messageWithoutTablePopoverTest() {
        ausPage.getAllFilter().select();
        List<Message> messagesList = ausPage.getMessages();
        Message messageWithoutTable = null;
        List<String> duMessagesListWithoutTable = new ArrayList<>();

        for (CSVRecord record : getDuMappingRecords()) {
            String text = record.get("Lender Text");
            if (!(text.contains("|"))) {
                duMessagesListWithoutTable.add(text);
            }
        }

        for (Message message : messagesList) {
            if (duMessagesListWithoutTable.contains(message.getText())) {
                messageWithoutTable = message;
                break;
            }
        }

        assertNotNull("Message without table not found", messageWithoutTable);
        String messageWithoutTableText = messageWithoutTable.getText();
        messageWithoutTable.hover();
        sleep(POPOVER_TIME);
        assertEquals("Popover doesn't contain correct text", messageWithoutTableText, ausPage.getPopoverText());
    }


    public static boolean isMessageWithParameterCorrespondsToDuMessage(String message, List<String> duMessages) {
        boolean isMessageInCorrectCategory = false;
        String[] messageParts;
        for (String duMessage : duMessages) {
            if (duMessage.contains("|")) {
                duMessage = duMessage.split("|")[0]; /* FIXME Warning:(550, 45) Suspicious regex expression "|" in call to 'split()'
                                                                    Warning:(550, 46) Empty branch in alternation */
            }
            if (duMessage.contains("%a")) {
                messageParts = duMessage.split("%a");
                if (message.contains(messageParts[0]) & message.contains(messageParts[1])) {
                    isMessageInCorrectCategory = true;
                    break;
                }
            } else {
                message.contains(duMessage); // FIXME Result of 'String.contains()' is ignored
                isMessageInCorrectCategory = true;
                break;
            }
        }
        return isMessageInCorrectCategory;
    }
}