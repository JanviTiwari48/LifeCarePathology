package com.janvi.lifecarepathology.sample.entity;

import com.janvi.lifecarepathology.booking.entity.Booking;
import com.janvi.lifecarepathology.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "samples")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sample extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private Booking booking;

    @Column(nullable = false, unique = true)
    private String sampleCode; // barcode/label id, e.g. "SMP-2026-00042"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SampleStatus status = SampleStatus.PENDING_COLLECTION;

    private LocalDateTime collectedAt;

    private String collectedBy; // technician name/id for now — proper User FK can come later if needed
}