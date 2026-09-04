package com.janvi.lifecarepathology.result.entity;

import com.janvi.lifecarepathology.common.entity.BaseEntity;
import com.janvi.lifecarepathology.sample.entity.Sample;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "sample_id", nullable = false, unique = true)
    private Sample sample;

    @Lob
    @Column(nullable = false)
    private String resultData; // structured text/JSON of parameter:value pairs — kept simple for now

    private String remarks;

    private String enteredBy;

    @Column(nullable = false)
    private LocalDateTime enteredAt;

    @Column(nullable = false)
    private boolean verified = false; // a second technician/doctor can verify before report generation
}