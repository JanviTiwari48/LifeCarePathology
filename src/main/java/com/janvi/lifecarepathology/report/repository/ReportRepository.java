package com.janvi.lifecarepathology.report.repository;

import com.janvi.lifecarepathology.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByResultId(Long resultId);
}