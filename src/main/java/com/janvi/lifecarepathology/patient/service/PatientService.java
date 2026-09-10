package com.janvi.lifecarepathology.patient.service;

import com.janvi.lifecarepathology.patient.dto.PatientRequest;
import com.janvi.lifecarepathology.patient.dto.PatientResponse;

import java.util.List;

public interface PatientService {
    PatientResponse createPatient(Long userId, PatientRequest request);
    PatientResponse getPatientById(Long id);
    List<PatientResponse> getAllPatients();
    PatientResponse updatePatient(Long id, PatientRequest request);
    void deletePatient(Long id);
}