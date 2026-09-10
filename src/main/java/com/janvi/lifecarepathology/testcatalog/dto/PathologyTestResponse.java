package com.janvi.lifecarepathology.testcatalog.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PathologyTestResponse {
    private Long id;
    private String testName;
    private String testCode;
    private String description;
    private BigDecimal price;
    private String sampleType;
    private String normalRange;
    private boolean active;
}