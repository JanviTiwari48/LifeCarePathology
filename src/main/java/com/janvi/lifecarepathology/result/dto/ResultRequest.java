package com.janvi.lifecarepathology.result.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultRequest {

    @NotBlank(message = "Result data is required")
    private String resultData;

    private String remarks; // optional

    @NotBlank(message = "Entered by is required")
    private String enteredBy;
}