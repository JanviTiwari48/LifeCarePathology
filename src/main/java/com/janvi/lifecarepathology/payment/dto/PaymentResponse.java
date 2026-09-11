package com.janvi.lifecarepathology.payment.dto;

import com.janvi.lifecarepathology.payment.entity.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentResponse {
    private Long id;
    private Long bookingId;
    private BigDecimal amount;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private PaymentStatus status;
    private LocalDateTime paidAt;
    // razorpaySignature deliberately excluded — a verification secret, never needs to go back to the client
}