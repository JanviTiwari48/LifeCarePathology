package com.janvi.lifecarepathology.result.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResultResponse {
    private Long id;
    private Long sampleId;
    private String resultData;
    private String remarks;
    private String enteredBy;
    private LocalDateTime enteredAt;
    private boolean verified;
}