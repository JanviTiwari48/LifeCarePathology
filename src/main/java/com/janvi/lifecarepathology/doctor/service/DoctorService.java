package com.janvi.lifecarepathology.doctor.service;

import com.janvi.lifecarepathology.doctor.dto.DoctorRequest;
import com.janvi.lifecarepathology.doctor.dto.DoctorResponse;

import java.util.List;

public interface DoctorService {
    DoctorResponse createDoctor(Long userId, DoctorRequest request);
    DoctorResponse getDoctorById(Long id);
    List<DoctorResponse> getAllDoctors();
    DoctorResponse updateDoctor(Long id, DoctorRequest request);
    void deleteDoctor(Long id);
}