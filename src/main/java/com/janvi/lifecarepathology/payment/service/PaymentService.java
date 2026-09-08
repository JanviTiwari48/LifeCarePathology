package com.janvi.lifecarepathology.payment.service;

import com.janvi.lifecarepathology.payment.entity.Payment;

public interface PaymentService {
    Payment createPaymentRecord(Long bookingId);
    Payment getPaymentById(Long id);
    Payment markSuccess(Long id, String razorpayPaymentId, String razorpaySignature);
    Payment markFailed(Long id);
}