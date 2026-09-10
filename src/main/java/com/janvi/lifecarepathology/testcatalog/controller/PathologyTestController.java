package com.janvi.lifecarepathology.testcatalog.controller;

import com.janvi.lifecarepathology.testcatalog.dto.PathologyTestRequest;
import com.janvi.lifecarepathology.testcatalog.dto.PathologyTestResponse;
import com.janvi.lifecarepathology.testcatalog.service.PathologyTestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class PathologyTestController {

    private final PathologyTestService testService;

    @PostMapping
    public ResponseEntity<PathologyTestResponse> createTest(@Valid @RequestBody PathologyTestRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testService.createTest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PathologyTestResponse> getTestById(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getTestById(id));
    }

    @GetMapping
    public ResponseEntity<List<PathologyTestResponse>> getAllTests() {
        return ResponseEntity.ok(testService.getAllTests());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PathologyTestResponse>> getActiveTests() {
        return ResponseEntity.ok(testService.getActiveTests());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PathologyTestResponse> updateTest(@PathVariable Long id, @Valid @RequestBody PathologyTestRequest request) {
        return ResponseEntity.ok(testService.updateTest(id, request));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateTest(@PathVariable Long id) {
        testService.deactivateTest(id);
        return ResponseEntity.noContent().build();
    }
}