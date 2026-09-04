package com.janvi.lifecarepathology.patient.entity;

import com.janvi.lifecarepathology.common.entity.BaseEntity;
import com.janvi.lifecarepathology.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String gender; // simple String for now — Phase 2 stays beginner-friendly, no enum needed yet

    private String address;

    private String bloodGroup;
}