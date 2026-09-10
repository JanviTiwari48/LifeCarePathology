package com.janvi.lifecarepathology.testcatalog.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PathologyTestRequest {

    @NotBlank(message = "Test name is required")
    private String testName;

    @NotBlank(message = "Test code is required")
    private String testCode;

    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    private String sampleType;

    private String normalRange;
}