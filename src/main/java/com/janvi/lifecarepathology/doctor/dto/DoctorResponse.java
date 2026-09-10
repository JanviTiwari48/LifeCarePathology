package com.janvi.lifecarepathology.doctor.dto;

import com.janvi.lifecarepathology.user.dto.UserResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResponse {
    private Long id;
    private UserResponse user;
    private String specialization;
    private String qualification;
    private Integer yearsOfExperience;
}