package com.janvi.lifecarepathology.testcatalog.controller;

import com.janvi.lifecarepathology.testcatalog.entity.PathologyTest;
import com.janvi.lifecarepathology.testcatalog.service.PathologyTestService;
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
    public ResponseEntity<PathologyTest> createTest(@RequestBody PathologyTest test) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testService.createTest(test));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PathologyTest> getTestById(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getTestById(id));
    }

    @GetMapping
    public ResponseEntity<List<PathologyTest>> getAllTests() {
        return ResponseEntity.ok(testService.getAllTests());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PathologyTest>> getActiveTests() {
        return ResponseEntity.ok(testService.getActiveTests());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PathologyTest> updateTest(@PathVariable Long id, @RequestBody PathologyTest test) {
        return ResponseEntity.ok(testService.updateTest(id, test));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateTest(@PathVariable Long id) {
        testService.deactivateTest(id);
        return ResponseEntity.noContent().build();
    }
}