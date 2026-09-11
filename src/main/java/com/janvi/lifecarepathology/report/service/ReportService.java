package com.janvi.lifecarepathology.report.service;

import com.janvi.lifecarepathology.report.dto.ReportResponse;

import java.util.List;

public interface ReportService {
    ReportResponse generateReport(Long resultId, String reportNumber);
    ReportResponse getReportById(Long id);
    List<ReportResponse> getAllReports();
    ReportResponse markDownloaded(Long id);
}