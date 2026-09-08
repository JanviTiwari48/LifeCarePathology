package com.janvi.lifecarepathology.patient.service;

import com.janvi.lifecarepathology.patient.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(Long userId, Patient patient);
    Patient getPatientById(Long id);
    List<Patient> getAllPatients();
    Patient updatePatient(Long id, Patient updatedPatient);
    void deletePatient(Long id);
}