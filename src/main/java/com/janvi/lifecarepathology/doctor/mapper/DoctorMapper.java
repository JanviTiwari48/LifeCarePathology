package com.janvi.lifecarepathology.doctor.mapper;

import com.janvi.lifecarepathology.doctor.dto.DoctorRequest;
import com.janvi.lifecarepathology.doctor.dto.DoctorResponse;
import com.janvi.lifecarepathology.doctor.entity.Doctor;
import com.janvi.lifecarepathology.user.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    private final UserMapper userMapper;

    public DoctorMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Doctor toEntity(DoctorRequest request) {
        Doctor doctor = new Doctor();
        doctor.setSpecialization(request.getSpecialization());
        doctor.setQualification(request.getQualification());
        doctor.setYearsOfExperience(request.getYearsOfExperience());
        return doctor;
    }

    public DoctorResponse toResponse(Doctor doctor) {
        DoctorResponse response = new DoctorResponse();
        response.setId(doctor.getId());
        response.setUser(userMapper.toResponse(doctor.getUser()));
        response.setSpecialization(doctor.getSpecialization());
        response.setQualification(doctor.getQualification());
        response.setYearsOfExperience(doctor.getYearsOfExperience());
        return response;
    }
}