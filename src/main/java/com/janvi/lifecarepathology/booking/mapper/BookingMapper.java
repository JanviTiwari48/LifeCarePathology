package com.janvi.lifecarepathology.booking.mapper;

import com.janvi.lifecarepathology.booking.dto.BookingResponse;
import com.janvi.lifecarepathology.booking.entity.Booking;
import com.janvi.lifecarepathology.doctor.mapper.DoctorMapper;
import com.janvi.lifecarepathology.patient.mapper.PatientMapper;
import com.janvi.lifecarepathology.testcatalog.mapper.PathologyTestMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookingMapper {

    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final PathologyTestMapper testMapper;

    public BookingMapper(PatientMapper patientMapper, DoctorMapper doctorMapper, PathologyTestMapper testMapper) {
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
        this.testMapper = testMapper;
    }

    public BookingResponse toResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setPatient(patientMapper.toResponse(booking.getPatient()));

        if (booking.getDoctor() != null) {
            response.setDoctor(doctorMapper.toResponse(booking.getDoctor()));
        }
        // else: response.doctor stays null — no doctor was linked to this booking

        response.setTests(booking.getTests().stream()
                .map(testMapper::toResponse)
                .collect(Collectors.toSet()));

        response.setBookingDate(booking.getBookingDate());
        response.setStatus(booking.getStatus());
        response.setTotalAmount(booking.getTotalAmount());
        return response;
    }

    // no toEntity() here — Booking creation logic is more involved (looking up
    // Patient/Doctor/Tests from IDs) and stays in BookingServiceImpl, same as Phase 3/4
}