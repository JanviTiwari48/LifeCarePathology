package com.janvi.lifecarepathology.report.controller;

import com.janvi.lifecarepathology.report.entity.Report;
import com.janvi.lifecarepathology.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/result/{resultId}")
    public ResponseEntity<Report> generateReport(
            @PathVariable Long resultId,
            @RequestParam String reportNumber) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reportService.generateReport(resultId, reportNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @PatchMapping("/{id}/downloaded")
    public ResponseEntity<Report> markDownloaded(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.markDownloaded(id));
    }
}