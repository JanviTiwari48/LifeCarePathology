package com.janvi.lifecarepathology.result.repository;

import com.janvi.lifecarepathology.result.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Optional<Result> findBySampleId(Long sampleId);
}