package com.janvi.lifecarepathology.booking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookingRequest {
    private Long patientId;
    private Long doctorId; // optional — can be null
    private Set<Long> testIds;
}