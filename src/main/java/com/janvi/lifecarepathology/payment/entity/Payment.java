package com.janvi.lifecarepathology.payment.entity;

import com.janvi.lifecarepathology.booking.entity.Booking;
import com.janvi.lifecarepathology.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private Booking booking;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(unique = true)
    private String razorpayOrderId; // set when order is created (Phase 10)

    @Column(unique = true)
    private String razorpayPaymentId; // set only after successful payment

    private String razorpaySignature; // used to verify payment authenticity

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.CREATED;

    private LocalDateTime paidAt;
}