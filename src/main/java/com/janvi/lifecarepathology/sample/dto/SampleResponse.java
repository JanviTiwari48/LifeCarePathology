package com.janvi.lifecarepathology.sample.dto;

import com.janvi.lifecarepathology.sample.entity.SampleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SampleResponse {
    private Long id;
    private Long bookingId; // reference by id, not nested BookingResponse — see reasoning above
    private String sampleCode;
    private SampleStatus status;
    private LocalDateTime collectedAt;
    private String collectedBy;
}