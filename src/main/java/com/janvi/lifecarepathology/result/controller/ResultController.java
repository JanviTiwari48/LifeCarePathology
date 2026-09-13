package com.janvi.lifecarepathology.result.controller;

import com.janvi.lifecarepathology.result.dto.ResultRequest;
import com.janvi.lifecarepathology.result.dto.ResultResponse;
import com.janvi.lifecarepathology.result.service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping("/sample/{sampleId}")
    @PreAuthorize("hasAnyRole('LAB_TECHNICIAN', 'ADMIN')")
    public ResponseEntity<ResultResponse> enterResult(
            @PathVariable Long sampleId,
            @Valid @RequestBody ResultRequest request) {
        ResultResponse result = resultService.enterResult(
                sampleId, request.getResultData(), request.getRemarks(), request.getEnteredBy());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('LAB_TECHNICIAN', 'ADMIN', 'DOCTOR')")
    public ResponseEntity<ResultResponse> getResultById(@PathVariable Long id) {
        return ResponseEntity.ok(resultService.getResultById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('LAB_TECHNICIAN', 'ADMIN', 'DOCTOR')")
    public ResponseEntity<List<ResultResponse>> getAllResults() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @PatchMapping("/{id}/verify")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<ResultResponse> verifyResult(@PathVariable Long id) {
        return ResponseEntity.ok(resultService.verifyResult(id));
    }
}