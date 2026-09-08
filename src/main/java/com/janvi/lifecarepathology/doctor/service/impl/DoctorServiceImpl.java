
package com.janvi.lifecarepathology.doctor.service.impl;

import com.janvi.lifecarepathology.doctor.entity.Doctor;
import com.janvi.lifecarepathology.doctor.repository.DoctorRepository;
import com.janvi.lifecarepathology.doctor.service.DoctorService;
import com.janvi.lifecarepathology.user.entity.User;
import com.janvi.lifecarepathology.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    @Override
    public Doctor createDoctor(Long userId, Doctor doctor) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        doctor.setUser(user);
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Doctor not found with id: " + id));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor existing = getDoctorById(id);
        existing.setSpecialization(updatedDoctor.getSpecialization());
        existing.setQualification(updatedDoctor.getQualification());
        existing.setYearsOfExperience(updatedDoctor.getYearsOfExperience());
        return doctorRepository.save(existing);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor existing = getDoctorById(id);
        doctorRepository.delete(existing);
    }
}