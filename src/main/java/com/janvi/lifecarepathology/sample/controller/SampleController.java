package com.janvi.lifecarepathology.sample.controller;

import com.janvi.lifecarepathology.sample.entity.Sample;
import com.janvi.lifecarepathology.sample.service.SampleService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/samples")
@RequiredArgsConstructor
@Validated
public class SampleController {

    private final SampleService sampleService;

    @PostMapping("/booking/{bookingId}")
    public ResponseEntity<Sample> collectSample(
            @PathVariable Long bookingId,
            @RequestParam @NotBlank(message = "Sample code is required") String sampleCode,
            @RequestParam @NotBlank(message = "Collected by is required") String collectedBy) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sampleService.collectSample(bookingId, sampleCode, collectedBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sample> getSampleById(@PathVariable Long id) {
        return ResponseEntity.ok(sampleService.getSampleById(id));
    }

    @GetMapping
    public ResponseEntity<List<Sample>> getAllSamples() {
        return ResponseEntity.ok(sampleService.getAllSamples());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Sample> updateStatus(
            @PathVariable Long id,
            @RequestParam @NotBlank(message = "Status is required") String status) {
        return ResponseEntity.ok(sampleService.updateSampleStatus(id, status));
    }
}