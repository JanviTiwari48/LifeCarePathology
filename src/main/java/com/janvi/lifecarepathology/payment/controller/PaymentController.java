package com.janvi.lifecarepathology.payment.controller;

import com.janvi.lifecarepathology.payment.entity.Payment;
import com.janvi.lifecarepathology.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/booking/{bookingId}")
    public ResponseEntity<Payment> createPaymentRecord(@PathVariable Long bookingId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPaymentRecord(bookingId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PatchMapping("/{id}/success")
    public ResponseEntity<Payment> markSuccess(
            @PathVariable Long id,
            @RequestParam String razorpayPaymentId,
            @RequestParam String razorpaySignature) {
        return ResponseEntity.ok(paymentService.markSuccess(id, razorpayPaymentId, razorpaySignature));
    }

    @PatchMapping("/{id}/failed")
    public ResponseEntity<Payment> markFailed(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.markFailed(id));
    }
}