package com.janvi.lifecarepathology.doctor.service.impl;

import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
import com.janvi.lifecarepathology.doctor.dto.DoctorRequest;
import com.janvi.lifecarepathology.doctor.dto.DoctorResponse;
import com.janvi.lifecarepathology.doctor.entity.Doctor;
import com.janvi.lifecarepathology.doctor.mapper.DoctorMapper;
import com.janvi.lifecarepathology.doctor.repository.DoctorRepository;
import com.janvi.lifecarepathology.doctor.service.DoctorService;
import com.janvi.lifecarepathology.user.entity.User;
import com.janvi.lifecarepathology.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorResponse createDoctor(Long userId, DoctorRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Doctor doctor = doctorMapper.toEntity(request);
        doctor.setUser(user);
        Doctor saved = doctorRepository.save(doctor);
        return doctorMapper.toResponse(saved);
    }

    @Override
    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        return doctorMapper.toResponse(doctor);
    }

    @Override
    public List<DoctorResponse> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toResponse)
                .toList();
    }

    @Override
    public DoctorResponse updateDoctor(Long id, DoctorRequest request) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        existing.setSpecialization(request.getSpecialization());
        existing.setQualification(request.getQualification());
        existing.setYearsOfExperience(request.getYearsOfExperience());
        Doctor saved = doctorRepository.save(existing);
        return doctorMapper.toResponse(saved);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctorRepository.delete(existing);
    }
}