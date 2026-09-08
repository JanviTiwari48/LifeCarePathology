package com.janvi.lifecarepathology.report.service;

import com.janvi.lifecarepathology.report.entity.Report;

import java.util.List;

public interface ReportService {
    Report generateReport(Long resultId, String reportNumber);
    Report getReportById(Long id);
    List<Report> getAllReports();
    Report markDownloaded(Long id);
}