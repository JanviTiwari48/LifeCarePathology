package com.janvi.lifecarepathology.payment.controller;

import com.janvi.lifecarepathology.payment.dto.PaymentResponse;
import com.janvi.lifecarepathology.payment.service.PaymentService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/booking/{bookingId}")
    public ResponseEntity<PaymentResponse> createPaymentRecord(@PathVariable Long bookingId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPaymentRecord(bookingId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PatchMapping("/{id}/success")
    public ResponseEntity<PaymentResponse> markSuccess(
            @PathVariable Long id,
            @RequestParam @NotBlank(message = "Razorpay payment id is required") String razorpayPaymentId,
            @RequestParam @NotBlank(message = "Razorpay signature is required") String razorpaySignature) {
        return ResponseEntity.ok(paymentService.markSuccess(id, razorpayPaymentId, razorpaySignature));
    }

    @PatchMapping("/{id}/failed")
    public ResponseEntity<PaymentResponse> markFailed(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.markFailed(id));
    }
}