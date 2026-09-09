package com.janvi.lifecarepathology.testcatalog.entity;

import com.janvi.lifecarepathology.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pathology_tests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PathologyTest extends BaseEntity {

    @NotBlank(message = "Test name is required")
    @Column(nullable = false, unique = true)
    private String testName;

    @NotBlank(message = "Test code is required")
    @Column(nullable = false)
    private String testCode;

    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Column(nullable = false)
    private BigDecimal price;

    private String sampleType;

    private String normalRange;

    @Column(nullable = false)
    private Boolean active = true;
}