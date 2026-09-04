package com.janvi.lifecarepathology.booking.entity;

import com.janvi.lifecarepathology.common.entity.BaseEntity;
import com.janvi.lifecarepathology.doctor.entity.Doctor;
import com.janvi.lifecarepathology.patient.entity.Patient;
import com.janvi.lifecarepathology.testcatalog.entity.PathologyTest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id") // nullable — a patient can self-book without a referring doctor
    private Doctor doctor;

    @ManyToMany
    @JoinTable(
            name = "booking_tests",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private Set<PathologyTest> tests = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status = BookingStatus.BOOKED;

    @Column(nullable = false)
    private BigDecimal totalAmount;
}