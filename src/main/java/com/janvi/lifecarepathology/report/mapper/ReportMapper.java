package com.janvi.lifecarepathology.report.mapper;

import com.janvi.lifecarepathology.report.dto.ReportResponse;
import com.janvi.lifecarepathology.report.entity.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public ReportResponse toResponse(Report report) {
        ReportResponse response = new ReportResponse();
        response.setId(report.getId());
        response.setResultId(report.getResult().getId());
        response.setReportNumber(report.getReportNumber());
        response.setPdfFilePath(report.getPdfFilePath());
        response.setGeneratedAt(report.getGeneratedAt());
        response.setDownloaded(report.isDownloaded());
        return response;
    }
}