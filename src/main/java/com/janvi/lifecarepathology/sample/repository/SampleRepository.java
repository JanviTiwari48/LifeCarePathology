package com.janvi.lifecarepathology.sample.repository;

import com.janvi.lifecarepathology.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SampleRepository extends JpaRepository<Sample, Long> {
    Optional<Sample> findByBookingId(Long bookingId);
}