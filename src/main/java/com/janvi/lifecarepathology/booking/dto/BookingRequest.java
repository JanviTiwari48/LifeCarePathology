package com.janvi.lifecarepathology.booking.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookingRequest {

    @NotNull(message = "Patient id is required")
    private Long patientId;

    private Long doctorId; // stays optional — no annotation needed

    @NotEmpty(message = "A booking must include at least one test")
    private Set<Long> testIds;
}