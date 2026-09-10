package com.janvi.lifecarepathology.patient.mapper;

import com.janvi.lifecarepathology.patient.dto.PatientRequest;
import com.janvi.lifecarepathology.patient.dto.PatientResponse;
import com.janvi.lifecarepathology.patient.entity.Patient;
import com.janvi.lifecarepathology.user.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    private final UserMapper userMapper;

    public PatientMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Patient toEntity(PatientRequest request) {
        Patient patient = new Patient();
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setAddress(request.getAddress());
        patient.setBloodGroup(request.getBloodGroup());
        return patient; // user gets set separately in the service, same as before
    }

    public PatientResponse toResponse(Patient patient) {
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setUser(userMapper.toResponse(patient.getUser())); // reuses UserMapper
        response.setDateOfBirth(patient.getDateOfBirth());
        response.setGender(patient.getGender());
        response.setAddress(patient.getAddress());
        response.setBloodGroup(patient.getBloodGroup());
        return response;
    }
}