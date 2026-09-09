package com.janvi.lifecarepathology.doctor.entity;

import com.janvi.lifecarepathology.common.entity.BaseEntity;
import com.janvi.lifecarepathology.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @NotBlank(message = "Specialization is required")
    @Column(nullable = false)
    private String specialization;

    @NotBlank(message = "Qualification is required")
    @Column(nullable = false)
    private String qualification;

    @PositiveOrZero(message = "Years of experience cannot be negative")
    private Integer yearsOfExperience;
}