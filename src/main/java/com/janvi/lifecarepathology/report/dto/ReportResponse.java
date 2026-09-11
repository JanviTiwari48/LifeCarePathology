package com.janvi.lifecarepathology.report.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportResponse {
    private Long id;
    private Long resultId;
    private String reportNumber;
    private String pdfFilePath;
    private LocalDateTime generatedAt;
    private boolean downloaded;
}