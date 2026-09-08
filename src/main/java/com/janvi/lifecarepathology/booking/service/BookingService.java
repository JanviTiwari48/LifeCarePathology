package com.janvi.lifecarepathology.booking.service;

import com.janvi.lifecarepathology.booking.dto.BookingRequest;
import com.janvi.lifecarepathology.booking.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequest request);
    Booking getBookingById(Long id);
    List<Booking> getAllBookings();
    List<Booking> getBookingsByPatient(Long patientId);
    void cancelBooking(Long id);
}