package com.capsilon.automation.aus.ui;

import com.capsilon.automation.aus.rest.ReportType;
import com.capsilon.automation.aus.ui.base.TestBase;
import com.capsilon.automation.aus.ui.component.Document;
import com.capsilon.automation.aus.ui.component.Message;
import com.capsilon.automation.aus.ui.component.MessageWithAssociatedDocs;
import com.capsilon.automation.aus.ui.component.NavigationElement;
import com.capsilon.automation.aus.utils.ReportUtils;
import com.capsilon.automation.aus.utils.UploadUtils;
import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.capsilon.automation.aus.rest.ReportType.DU;
import static com.capsilon.automation.aus.utils.ReportUtils.getLatestReport;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DocumentsReviewTest extends TestBase {


    @BeforeAll
    static void documentsUpload() {
        int initialDocuments = folderService.getLatestReport(folderId, DU).getReportData().getDocuments().size();

        uploadSmartDocument.uploadDocument(
            folderId, UploadUtils.getFilePath("Loan Application.pdf"));
        uploadSmartDocument.uploadDocument(
            folderId, UploadUtils.getFilePath("Paystub.pdf"));
        uploadSmartDocument.uploadDocument(
            folderId, UploadUtils.getFilePath("Appraisal.pdf"));
        uploadSmartDocument.uploadDocument(
            folderId, UploadUtils.getFilePath("IRS W-2.pdf"));

        int expectedDocs = initialDocuments + 4;
        UploadUtils.waitDocumentsSizeShouldBe(DOCUMENTS_UPLOAD_TIMEOUT, folderId, expectedDocs);

        reloadAUSApp();
    }


    @Test/*AUS-30*/
    public void markAsReviewDocumentTest() {
        Document unreadDocument = ausPage.getUnreadDocuments().get(0);

        assertEquals("Unread document doesn't have Red finding marker",
            FindingMarkerColor.RED, unreadDocument.getFindingMarker().getColor());

        unreadDocument.changeReviewedStatus();

        assertTrue("Reviewed document hasn't Gray finding marker",
            ausPage.checkFindingMarkerColor(unreadDocument, FindingMarkerColor.GREEN, REVIEW_TIMEOUT));
        assertEquals("Document doesn't have status \"Reviewed\" after reviewing",
            "Reviewed", unreadDocument.getDocumentStatus());

        unreadDocument.changeReviewedStatus();

        assertTrue("Reviewed message doesn't have Red finding marker after canceling review",
            ausPage.checkFindingMarkerColor(unreadDocument, FindingMarkerColor.RED, REVIEW_TIMEOUT));
        assertEquals("Document doesn't have status \"Not Reviewed\" after canceling review",
            "Not Reviewed", unreadDocument.getDocumentStatus());
    }

    @Test/*AUS-30*/
    public void hideReviewedDocumentsTest() {
        ausPage.getAllFilter().select();
        ausPage.getNotReviewedFilter().select();

        Document unreadDocument = ausPage.getUnreadDocuments().get(0);
        HashSet<String> unreadDocumentNames = new HashSet<>();
        String documentName = unreadDocument.getName();

        List<Document> unreadDocuments = ausPage.getUnreadDocuments();
        for (Document document : unreadDocuments) {
            unreadDocumentNames.add(document.getName());
        }

        assertEquals("Document doesn't have status \"Not Reviewed\" before reviewing",
            "Not Reviewed", unreadDocument.getDocumentStatus());
        assertEquals("Document shouldn't be reviewed",
            FindingMarkerColor.RED, unreadDocument.getFindingMarker().getColor());

        unreadDocument.changeReviewedStatus();

        assertTrue("Reviewed document hasn't GREEN finding marker",
            ausPage.checkFindingMarkerColor(unreadDocument, FindingMarkerColor.GREEN, REVIEW_TIMEOUT));
        assertEquals("Document should have status \"Reviewed\" after reviewing",
            "Reviewed", unreadDocument.getDocumentStatus());
        assertEquals("Documents count in Not Reviewed filter changes after review document and before refreshing filters",
            unreadDocuments.size(), ausPage.getDocuments().size());
        ausPage.getDocumentsCollection()
            .shouldHave(
                CollectionCondition
                    .anyMatch(
                        "Documents list in Not Reviewed doesn't contain message after document review and before refreshing filters",
                        element -> documentName.equals(element.getText())));

        ausPage.getAllFilter().select();
        ausPage.getNotReviewedFilter().select();

        unreadDocumentNames.clear();
        for (Document document : ausPage.getUnreadDocuments()) {
            unreadDocumentNames.add(document.getName());
        }

        assertFalse("Documents list in Not Reviewed filter contains reviewed document after reading message and refreshing",
            unreadDocumentNames.contains(documentName));
    }

    @Test/*AUS-30*/
    public void markAsReviewDocumentAfterReloadTest() {
        reloadAUSApp();
        Document unreadDocument = ausPage.getUnreadDocuments().get(0);
        String documentName = unreadDocument.getName();

        unreadDocument.changeReviewedStatus();

        assertTrue("Reviewed document has red finding marker",
            ausPage.checkFindingMarkerColor(unreadDocument, FindingMarkerColor.GREEN, REVIEW_TIMEOUT));

        ausPage.getAllFilter().select();
        ausPage.getNotReviewedFilter().select();

        List<Document> unreadDocuments = ausPage.getUnreadDocuments();
        for (Document document : unreadDocuments) {
            if (document.getName().equals(documentName)) {
                fail("Document wasn't be hidden after reviewing");
            }
        }

        int initialSize = folderService.getLatestReport(folderId, ReportType.DU).getReportData().getDocuments().size();
        uploadSmartDocument.uploadDocument(folderId, UploadUtils.getFilePath(documentName + ".pdf"));
        UploadUtils.waitDocumentsSizeShouldBe(DOCUMENTS_UPLOAD_TIMEOUT, folderId, initialSize + 1);
        reloadAUSApp();

        unreadDocuments = ausPage.getUnreadDocuments();
        boolean docIsPresent = false;
        for (Document document : unreadDocuments) {
            if (document.getName().equals(documentName)) {
                docIsPresent = true;
                break;
            }
        }
        assertTrue("Document doesn't appear after reload", docIsPresent);
    }

    @Test/*AUS-36*/
    public void filterCountsShouldBeCorrect() {
        folderWithReports = folderService.getFolderWithReports(folderId);
        report = getLatestReport(folderWithReports, ReportType.DU);

        ausPage.getAllFilter().select();

        int redBubblesCount = ausPage.getRedBubblesCount();
        int notReviewedItemsCount = ReportUtils.getNotReviewedItemsCount(report);

        ausPage.getNotReviewedFilter().getLabel().shouldHave(text("Not reviewed (" + redBubblesCount + ")"));
        assertEquals("Red bubbles count on UI not equal to not reviewed items from API response",
            notReviewedItemsCount, redBubblesCount);

        int allImportantItemsCount = ReportUtils.getAllImportantItemsCount(report);
        ausPage.getAllFilter().getLabel().shouldHave(text("All (" + allImportantItemsCount + ")"));
        assertEquals("Findings and Docs count not equal to all important items count from API response",
            allImportantItemsCount, ausPage.getFindingMarkers().size());
    }

    @Test/*AUS-84*/
    public void readOnlyReportsTest() {
        uploadDuReport();
        reloadAUSApp();

        assertFalse("Read only report is selected by default", ausPage.getReportSelector().isCurrentReportReadOnly());

        ausPage.getReportSelector().expand();
        assertFalse("Read only reports aren't present in DDL", ausPage.getReportSelector().getReadOnlyReports().isEmpty());
        ausPage.getReportSelector().chooseReadOnlyReport();
        assertTrue("Read only report hasn't been chosen", ausPage.getReportSelector().isCurrentReportReadOnly());

        sleep(1000);
        List<Message> messages = ausPage.getMessages();
        List<Message> messagesWithFindingMarker = messages
            .stream()
            .filter(NavigationElement::isFindingMarkerPresent)
            .collect(Collectors.toList());

        assertTrue("Finding markers present in Read Only report", messagesWithFindingMarker.isEmpty());

        List<Document> documentsWithReviewButton = ausPage.getDocuments()
            .stream()
            .filter(Document::isReviewButtonPresent)
            .collect(Collectors.toList());

        assertTrue("Review document buttons present in Read Only report", documentsWithReviewButton.isEmpty());

        Message message = messages.get(0);
        String messageText = message.getText();
        message.hover();
        sleep(POPOVER_TIME);

        assertEquals("Popover doesn't contain correct text", messageText, ausPage.getPopoverText());
    }

    @Test/*AUS-368*/
    @Order(1)
    public void associatedDocsSorting() {
        documentsUpload();
        reloadAUSApp();

        ausPage.getAllFilter().select();
        MessageWithAssociatedDocs messageWithAssociatedDocs;
        List<MessageWithAssociatedDocs> messagesWithAssociatedDocs = ausPage.getMessagesWithAssociatedDocs();
        assertFalse("Report doesn't contain messages with associated documents", messagesWithAssociatedDocs.isEmpty());
        messageWithAssociatedDocs = messagesWithAssociatedDocs.get(0);

        messageWithAssociatedDocs.expandAsscociatedDocs();

        List<Document> documents = messageWithAssociatedDocs.getDocuments();

        List<String> documentsNames = new ArrayList<>();
        List<String> sortedDocumentsNames = new ArrayList<>();
        List<String> documentsStatuses = new ArrayList<>();
        List<String> sortedDocumentsStatuses = new ArrayList<>();

        for (Document doc : documents) {
            documentsNames.add(doc.getName());
            sortedDocumentsNames.add(doc.getName());
            if (doc.getDocumentStatus().equals("Reviewed")) {
                doc.changeReviewedStatus();
            }
            sortedDocumentsStatuses.add(doc.getDocumentStatus());
        }
        Collections.sort(sortedDocumentsNames);

        String firstDocName = documentsNames.get(0);

        assertEquals("Documents aren't sorted alphabetically", sortedDocumentsNames, documentsNames);
        documents.get(0).changeReviewedStatus();
        sleep(1000);
        ausPage.getNotReviewedFilter().select();
        sleep(1000);
        ausPage.getAllFilter().select();
        sleep(1000);

        messageWithAssociatedDocs = messagesWithAssociatedDocs.get(0);
        messageWithAssociatedDocs.expandAsscociatedDocs();
        documents = messageWithAssociatedDocs.getDocuments();

        documentsNames.clear();
        for (Document doc : documents) {
            documentsStatuses.add(doc.getDocumentStatus());
            documentsNames.add(doc.getName());
        }
        sortedDocumentsStatuses.set(documents.size() - 1, "Reviewed");
        sortedDocumentsNames.remove(0);
        sortedDocumentsNames.add(firstDocName);

        assertEquals("Reviewed documents aren't at down of the list", sortedDocumentsStatuses, documentsStatuses);
        assertEquals("Documents with different statuses aren't sorted correct alphabetically", sortedDocumentsNames, documentsNames);
    }
}
