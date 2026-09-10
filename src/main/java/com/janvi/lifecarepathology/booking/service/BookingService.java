package com.janvi.lifecarepathology.booking.service;

import com.janvi.lifecarepathology.booking.dto.BookingRequest;
import com.janvi.lifecarepathology.booking.dto.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    BookingResponse getBookingById(Long id);
    List<BookingResponse> getAllBookings();
    List<BookingResponse> getBookingsByPatient(Long patientId);
    void cancelBooking(Long id);
}