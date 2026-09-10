package com.janvi.lifecarepathology.testcatalog.entity;

import com.janvi.lifecarepathology.common.entity.BaseEntity;
import jakarta.persistence.*;
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

    @Column(nullable = false, unique = true)
    private String testName;

    @Column(nullable = false)
    private String testCode;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String sampleType;

    private String normalRange;

    @Column(nullable = false)
    private Boolean active = true;
}