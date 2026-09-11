package com.janvi.lifecarepathology.payment.service;

import com.janvi.lifecarepathology.payment.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPaymentRecord(Long bookingId);
    PaymentResponse getPaymentById(Long id);
    PaymentResponse markSuccess(Long id, String razorpayPaymentId, String razorpaySignature);
    PaymentResponse markFailed(Long id);
}