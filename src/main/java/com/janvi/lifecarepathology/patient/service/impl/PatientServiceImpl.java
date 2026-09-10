package com.janvi.lifecarepathology.patient.service.impl;

import com.janvi.lifecarepathology.patient.entity.Patient;
import com.janvi.lifecarepathology.patient.repository.PatientRepository;
import com.janvi.lifecarepathology.patient.service.PatientService;
import com.janvi.lifecarepathology.user.entity.User;
import com.janvi.lifecarepathology.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Override
    public Patient createPatient(Long userId, Patient patient) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        patient.setUser(user);
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existing = getPatientById(id);
        existing.setDateOfBirth(updatedPatient.getDateOfBirth());
        existing.setGender(updatedPatient.getGender());
        existing.setAddress(updatedPatient.getAddress());
        existing.setBloodGroup(updatedPatient.getBloodGroup());
        return patientRepository.save(existing);
    }

    @Override
    public void deletePatient(Long id) {
        Patient existing = getPatientById(id);
        patientRepository.delete(existing);
    }
}