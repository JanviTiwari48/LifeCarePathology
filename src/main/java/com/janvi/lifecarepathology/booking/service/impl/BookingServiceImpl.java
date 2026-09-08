package com.janvi.lifecarepathology.booking.service.impl;

import com.janvi.lifecarepathology.booking.dto.BookingRequest;
import com.janvi.lifecarepathology.booking.entity.Booking;
import com.janvi.lifecarepathology.booking.entity.BookingStatus;
import com.janvi.lifecarepathology.booking.repository.BookingRepository;
import com.janvi.lifecarepathology.booking.service.BookingService;
import com.janvi.lifecarepathology.doctor.entity.Doctor;
import com.janvi.lifecarepathology.doctor.repository.DoctorRepository;
import com.janvi.lifecarepathology.patient.entity.Patient;
import com.janvi.lifecarepathology.patient.repository.PatientRepository;
import com.janvi.lifecarepathology.testcatalog.entity.PathologyTest;
import com.janvi.lifecarepathology.testcatalog.repository.PathologyTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PathologyTestRepository testRepository;

    @Override
    public Booking createBooking(BookingRequest request) {
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new NoSuchElementException("Patient not found with id: " + request.getPatientId()));

        Doctor doctor = null;
        if (request.getDoctorId() != null) {
            doctor = doctorRepository.findById(request.getDoctorId())
                    .orElseThrow(() -> new NoSuchElementException("Doctor not found with id: " + request.getDoctorId()));
        }

        Set<PathologyTest> tests = new HashSet<>(testRepository.findAllById(request.getTestIds()));
        if (tests.isEmpty()) {
            throw new IllegalArgumentException("A booking must include at least one valid test");
        }

        BigDecimal totalAmount = tests.stream()
                .map(PathologyTest::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Booking booking = new Booking();
        booking.setPatient(patient);
        booking.setDoctor(doctor);
        booking.setTests(tests);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus(BookingStatus.BOOKED);
        booking.setTotalAmount(totalAmount);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Booking not found with id: " + id));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingsByPatient(Long patientId) {
        return bookingRepository.findByPatientId(patientId);
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }
}