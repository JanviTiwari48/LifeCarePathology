package com.janvi.lifecarepathology.patient.dto;

import com.janvi.lifecarepathology.user.dto.UserResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientResponse {
    private Long id;
    private UserResponse user; // nested DTO, not the raw User entity
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String bloodGroup;
}