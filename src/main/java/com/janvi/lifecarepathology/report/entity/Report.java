package com.janvi.lifecarepathology.report.entity;

import com.janvi.lifecarepathology.common.entity.BaseEntity;
import com.janvi.lifecarepathology.result.entity.Result;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "result_id", nullable = false, unique = true)
    private Result result;

    @Column(nullable = false, unique = true)
    private String reportNumber; // e.g. "RPT-2026-00042"

    private String pdfFilePath; // populated in Phase 11 once PDF generation exists

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @Column(nullable = false)
    private boolean downloaded = false;
}