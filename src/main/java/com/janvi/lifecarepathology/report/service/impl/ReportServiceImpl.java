package com.janvi.lifecarepathology.report.service.impl;

import com.janvi.lifecarepathology.report.entity.Report;
import com.janvi.lifecarepathology.report.repository.ReportRepository;
import com.janvi.lifecarepathology.report.service.ReportService;
import com.janvi.lifecarepathology.result.entity.Result;
import com.janvi.lifecarepathology.result.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
import com.janvi.lifecarepathology.common.exception.BusinessRuleException;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ResultRepository resultRepository;

    @Override
    public Report generateReport(Long resultId, String reportNumber) {
        Result result = resultRepository.findById(resultId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with id: " + resultId));

        if (!result.isVerified()) {
            throw new BusinessRuleException("Cannot generate a report for an unverified result");
        }

        Report report = new Report();
        report.setResult(result);
        report.setReportNumber(reportNumber);
        report.setGeneratedAt(LocalDateTime.now());
        report.setDownloaded(false);
        // pdfFilePath stays null until Phase 11 generates the actual PDF

        return reportRepository.save(report);
    }

    @Override
    public Report getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public Report markDownloaded(Long id) {
        Report report = getReportById(id);
        report.setDownloaded(true);
        return reportRepository.save(report);
    }
}
