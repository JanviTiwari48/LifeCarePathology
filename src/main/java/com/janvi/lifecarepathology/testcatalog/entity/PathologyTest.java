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
    private String testName; // e.g. "Complete Blood Count (CBC)"

    @Column(nullable = false)
    private String testCode; // e.g. "CBC001" — used in reports/labels

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String sampleType; // e.g. "Blood", "Urine" — informs Sample Management later

    private String normalRange; // e.g. "4.5-11.0 x10^9/L" — plain text for now

    @Column(nullable = false)
    private boolean active = true; // lets Admin retire a test without deleting history
}