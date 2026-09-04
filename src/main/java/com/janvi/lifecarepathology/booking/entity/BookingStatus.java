package com.janvi.lifecarepathology.booking.entity;

public enum BookingStatus {
    BOOKED,           // created, payment not yet done
    PAYMENT_PENDING,
    PAID,
    SAMPLE_COLLECTED,
    RESULT_ENTERED,
    REPORT_GENERATED,
    CANCELLED
}