package com.janvi.lifecarepathology.booking.service.impl;

import com.janvi.lifecarepathology.booking.dto.BookingRequest;
import com.janvi.lifecarepathology.booking.dto.BookingResponse;
import com.janvi.lifecarepathology.booking.entity.Booking;
import com.janvi.lifecarepathology.booking.entity.BookingStatus;
import com.janvi.lifecarepathology.booking.mapper.BookingMapper;
import com.janvi.lifecarepathology.booking.repository.BookingRepository;
import com.janvi.lifecarepathology.booking.service.BookingService;
import com.janvi.lifecarepathology.common.exception.BusinessRuleException;
import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
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
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PathologyTestRepository testRepository;
    private final BookingMapper bookingMapper;

    @Override
    public BookingResponse createBooking(BookingRequest request) {
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + request.getPatientId()));

        Doctor doctor = null;
        if (request.getDoctorId() != null) {
            doctor = doctorRepository.findById(request.getDoctorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + request.getDoctorId()));
        }

        Set<PathologyTest> tests = new HashSet<>(testRepository.findAllById(request.getTestIds()));
        if (tests.isEmpty()) {
            throw new BusinessRuleException("A booking must include at least one valid test");
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

        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toResponse(saved);
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        return bookingMapper.toResponse(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toResponse)
                .toList();
    }

    @Override
    public List<BookingResponse> getBookingsByPatient(Long patientId) {
        return bookingRepository.findByPatientId(patientId).stream()
                .map(bookingMapper::toResponse)
                .toList();
    }

    @Override
    public void updateBookingStatus(Long bookingId, BookingStatus newStatus) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        if (!booking.getStatus().canTransitionTo(newStatus)) {
            throw new BusinessRuleException(
                    "Cannot move booking from " + booking.getStatus() + " to " + newStatus);
        }

        booking.setStatus(newStatus);
        bookingRepository.save(booking);
    }
    @Override
    public void cancelBooking(Long id) {
        updateBookingStatus(id, BookingStatus.CANCELLED);
    }


}