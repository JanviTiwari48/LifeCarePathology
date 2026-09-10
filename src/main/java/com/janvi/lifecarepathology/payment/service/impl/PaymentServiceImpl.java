package com.janvi.lifecarepathology.payment.service.impl;

import com.janvi.lifecarepathology.booking.entity.Booking;
import com.janvi.lifecarepathology.booking.repository.BookingRepository;
import com.janvi.lifecarepathology.payment.entity.Payment;
import com.janvi.lifecarepathology.payment.entity.PaymentStatus;
import com.janvi.lifecarepathology.payment.repository.PaymentRepository;
import com.janvi.lifecarepathology.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Override
    public Payment createPaymentRecord(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(booking.getTotalAmount());
        payment.setStatus(PaymentStatus.CREATED);
        // razorpayOrderId gets set in Phase 10 when the real Razorpay order is created

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
    }

    @Override
    public Payment markSuccess(Long id, String razorpayPaymentId, String razorpaySignature) {
        Payment payment = getPaymentById(id);
        payment.setRazorpayPaymentId(razorpayPaymentId);
        payment.setRazorpaySignature(razorpaySignature);
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaidAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    @Override
    public Payment markFailed(Long id) {
        Payment payment = getPaymentById(id);
        payment.setStatus(PaymentStatus.FAILED);
        return paymentRepository.save(payment);
    }
}