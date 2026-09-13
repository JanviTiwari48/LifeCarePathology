package com.janvi.lifecarepathology.testcatalog.controller;

import com.janvi.lifecarepathology.testcatalog.dto.PathologyTestRequest;
import com.janvi.lifecarepathology.testcatalog.dto.PathologyTestResponse;
import com.janvi.lifecarepathology.testcatalog.service.PathologyTestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class PathologyTestController {

    private final PathologyTestService testService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PathologyTestResponse> createTest(@Valid @RequestBody PathologyTestRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testService.createTest(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PathologyTestResponse> getTestById(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getTestById(id));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PathologyTestResponse>> getAllTests() {
        return ResponseEntity.ok(testService.getAllTests());
    }

    @GetMapping("/active")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PathologyTestResponse>> getActiveTests() {
        return ResponseEntity.ok(testService.getActiveTests());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PathologyTestResponse> updateTest(@PathVariable Long id, @Valid @RequestBody PathologyTestRequest request) {
        return ResponseEntity.ok(testService.updateTest(id, request));
    }

    @PatchMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deactivateTest(@PathVariable Long id) {
        testService.deactivateTest(id);
        return ResponseEntity.noContent().build();
    }
}