package com.janvi.lifecarepathology.payment.entity;

public enum PaymentStatus {
    CREATED,   // Razorpay order created, not yet paid
    SUCCESS,
    FAILED,
    REFUNDED
}