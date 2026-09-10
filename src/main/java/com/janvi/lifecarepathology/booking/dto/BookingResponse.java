package com.janvi.lifecarepathology.booking.dto;

import com.janvi.lifecarepathology.booking.entity.BookingStatus;
import com.janvi.lifecarepathology.doctor.dto.DoctorResponse;
import com.janvi.lifecarepathology.patient.dto.PatientResponse;
import com.janvi.lifecarepathology.testcatalog.dto.PathologyTestResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class BookingResponse {
    private Long id;
    private PatientResponse patient;
    private DoctorResponse doctor; // null if no doctor was linked — that's fine, matches the optional relationship
    private Set<PathologyTestResponse> tests;
    private LocalDateTime bookingDate;
    private BookingStatus status;
    private BigDecimal totalAmount;
}