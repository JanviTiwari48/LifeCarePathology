package com.janvi.lifecarepathology.doctor.service;

import com.janvi.lifecarepathology.doctor.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor createDoctor(Long userId, Doctor doctor);
    Doctor getDoctorById(Long id);
    List<Doctor> getAllDoctors();
    Doctor updateDoctor(Long id, Doctor updatedDoctor);
    void deleteDoctor(Long id);
}