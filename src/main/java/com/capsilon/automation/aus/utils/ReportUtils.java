package com.capsilon.automation.aus.utils;

import com.capsilon.automation.aus.dto.Document;
import com.capsilon.automation.aus.dto.DuReport;
import com.capsilon.automation.aus.dto.Finding;
import com.capsilon.automation.aus.dto.FolderWithReports;
import com.capsilon.automation.aus.rest.ReportType;

import java.util.Collection;
import java.util.HashMap;

public class ReportUtils {

    public static int getAllImportantItemsCount(DuReport report) {
        return report.getReportData().getFindings().values()
            .stream()
            .filter(Finding::getVisible)
            .reduce(0, (count, finding) -> count + 1 + finding.getDocuments().size(), Integer::sum);
    }

    public static int getNotReviewedItemsCount(DuReport report) {
        return report.getReportData().getFindings().values()
            .stream()
            .filter(Finding::getVisible)
            .reduce(
                0,
                (count, finding) -> count
                    + (finding.getRead() ? 0 : 1)
                    + (int) finding.getDocuments()
                    .stream()
                    .filter(document -> !report.getReportData().getDocuments().get(document).isReviewed())
                    .count(),
                Integer::sum
            );
    }

    public static String getRecommendation(DuReport report) {
        return report.getReportData().getSummary().getRecommendation();
    }

    public static Collection<Finding> getFindings(DuReport report) {
        return report.getReportData().getFindings().values();
    }

    public static DuReport getLatestReport(FolderWithReports folderWithReports, ReportType reportType) {
        return folderWithReports
            .getLatestReports()
            .stream()
            .filter(report -> report.getReportType().equals(reportType))
            .findFirst().orElseThrow();
    }

    public static HashMap<String, Document> getDocuments(DuReport report) {
        return report.getReportData().getDocuments();
    }
}
