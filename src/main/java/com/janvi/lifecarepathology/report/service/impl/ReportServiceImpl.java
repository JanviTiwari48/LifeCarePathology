package com.janvi.lifecarepathology.report.service.impl;

import com.janvi.lifecarepathology.booking.entity.BookingStatus;
import com.janvi.lifecarepathology.booking.service.BookingService;
import com.janvi.lifecarepathology.common.exception.BusinessRuleException;
import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
import com.janvi.lifecarepathology.report.dto.ReportResponse;
import com.janvi.lifecarepathology.report.entity.Report;
import com.janvi.lifecarepathology.report.mapper.ReportMapper;
import com.janvi.lifecarepathology.report.repository.ReportRepository;
import com.janvi.lifecarepathology.report.service.ReportService;
import com.janvi.lifecarepathology.result.entity.Result;
import com.janvi.lifecarepathology.result.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ResultRepository resultRepository;
    private final ReportMapper reportMapper;
    private final BookingService bookingService; // NEW

    @Override
    public ReportResponse generateReport(Long resultId, String reportNumber) {
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

        Report saved = reportRepository.save(report);

        // NEW — final advance: the booking's workflow is now complete
        Long bookingId = result.getSample().getBooking().getId();
        bookingService.updateBookingStatus(bookingId, BookingStatus.REPORT_GENERATED);

        return reportMapper.toResponse(saved);
    }

    @Override
    public ReportResponse getReportById(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));
        return reportMapper.toResponse(report);
    }

    @Override
    public List<ReportResponse> getAllReports() {
        return reportRepository.findAll().stream()
                .map(reportMapper::toResponse)
                .toList();
    }

    @Override
    public ReportResponse markDownloaded(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));
        report.setDownloaded(true);
        Report saved = reportRepository.save(report);
        return reportMapper.toResponse(saved);
    }
}