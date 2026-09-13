package com.janvi.lifecarepathology.payment.service.impl;

import com.janvi.lifecarepathology.booking.entity.Booking;
import com.janvi.lifecarepathology.booking.entity.BookingStatus;
import com.janvi.lifecarepathology.booking.repository.BookingRepository;
import com.janvi.lifecarepathology.booking.service.BookingService;
import com.janvi.lifecarepathology.common.exception.BusinessRuleException;
import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
import com.janvi.lifecarepathology.payment.dto.PaymentResponse;
import com.janvi.lifecarepathology.payment.entity.Payment;
import com.janvi.lifecarepathology.payment.entity.PaymentStatus;
import com.janvi.lifecarepathology.payment.mapper.PaymentMapper;
import com.janvi.lifecarepathology.payment.repository.PaymentRepository;
import com.janvi.lifecarepathology.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentMapper paymentMapper;
    private final BookingService bookingService;

    @Override
    public PaymentResponse createPaymentRecord(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        if (paymentRepository.findByBookingId(bookingId).isPresent()) {
            throw new BusinessRuleException("A payment record already exists for booking id: " + bookingId);
        }

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(booking.getTotalAmount());
        payment.setStatus(PaymentStatus.CREATED);
        // razorpayOrderId intentionally left null — no real gateway wired up;
        // markSuccess/markFailed are called manually to simulate a payment outcome

        Payment saved = paymentRepository.save(payment);
        return paymentMapper.toResponse(saved);
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        return paymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponse markSuccess(Long id, String razorpayPaymentId, String razorpaySignature) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        payment.setRazorpayPaymentId(razorpayPaymentId);
        payment.setRazorpaySignature(razorpaySignature);
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaidAt(LocalDateTime.now());
        Payment saved = paymentRepository.save(payment);

        bookingService.updateBookingStatus(payment.getBooking().getId(), BookingStatus.PAID);

        return paymentMapper.toResponse(saved);
    }

    @Override
    public PaymentResponse markFailed(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        payment.setStatus(PaymentStatus.FAILED);
        Payment saved = paymentRepository.save(payment);
        return paymentMapper.toResponse(saved);
    }
}