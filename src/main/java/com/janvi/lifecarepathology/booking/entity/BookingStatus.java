package com.janvi.lifecarepathology.booking.entity;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum BookingStatus {
    BOOKED,
    PAYMENT_PENDING,
    PAID,
    SAMPLE_COLLECTED,
    RESULT_ENTERED,
    REPORT_GENERATED,
    CANCELLED;

    private static final Map<BookingStatus, Set<BookingStatus>> ALLOWED_TRANSITIONS = new EnumMap<>(BookingStatus.class);

    static {
        ALLOWED_TRANSITIONS.put(BOOKED, EnumSet.of(PAYMENT_PENDING, PAID, CANCELLED));
        ALLOWED_TRANSITIONS.put(PAYMENT_PENDING, EnumSet.of(PAID, CANCELLED));
        ALLOWED_TRANSITIONS.put(PAID, EnumSet.of(SAMPLE_COLLECTED, CANCELLED));
        ALLOWED_TRANSITIONS.put(SAMPLE_COLLECTED, EnumSet.of(RESULT_ENTERED));
        ALLOWED_TRANSITIONS.put(RESULT_ENTERED, EnumSet.of(REPORT_GENERATED));
        ALLOWED_TRANSITIONS.put(REPORT_GENERATED, EnumSet.noneOf(BookingStatus.class)); // terminal
        ALLOWED_TRANSITIONS.put(CANCELLED, EnumSet.noneOf(BookingStatus.class)); // terminal
    }

    public boolean canTransitionTo(BookingStatus target) {
        return ALLOWED_TRANSITIONS.getOrDefault(this, Set.of()).contains(target);
    }
}