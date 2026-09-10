package com.janvi.lifecarepathology.patient.service.impl;

import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
import com.janvi.lifecarepathology.patient.dto.PatientRequest;
import com.janvi.lifecarepathology.patient.dto.PatientResponse;
import com.janvi.lifecarepathology.patient.entity.Patient;
import com.janvi.lifecarepathology.patient.mapper.PatientMapper;
import com.janvi.lifecarepathology.patient.repository.PatientRepository;
import com.janvi.lifecarepathology.patient.service.PatientService;
import com.janvi.lifecarepathology.user.entity.User;
import com.janvi.lifecarepathology.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientResponse createPatient(Long userId, PatientRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Patient patient = patientMapper.toEntity(request);
        patient.setUser(user);
        Patient saved = patientRepository.save(patient);
        return patientMapper.toResponse(saved);
    }

    @Override
    public PatientResponse getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        return patientMapper.toResponse(patient);
    }

    @Override
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toResponse)
                .toList();
    }

    @Override
    public PatientResponse updatePatient(Long id, PatientRequest request) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        existing.setDateOfBirth(request.getDateOfBirth());
        existing.setGender(request.getGender());
        existing.setAddress(request.getAddress());
        existing.setBloodGroup(request.getBloodGroup());
        Patient saved = patientRepository.save(existing);
        return patientMapper.toResponse(saved);
    }

    @Override
    public void deletePatient(Long id) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        patientRepository.delete(existing);
    }
}