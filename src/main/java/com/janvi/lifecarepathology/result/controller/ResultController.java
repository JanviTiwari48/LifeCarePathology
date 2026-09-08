package com.janvi.lifecarepathology.result.controller;

import com.janvi.lifecarepathology.result.entity.Result;
import com.janvi.lifecarepathology.result.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping("/sample/{sampleId}")
    public ResponseEntity<Result> enterResult(
            @PathVariable Long sampleId,
            @RequestBody java.util.Map<String, String> body) {
        Result result = resultService.enterResult(
                sampleId,
                body.get("resultData"),
                body.get("remarks"),
                body.get("enteredBy"));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Long id) {
        return ResponseEntity.ok(resultService.getResultById(id));
    }

    @GetMapping
    public ResponseEntity<List<Result>> getAllResults() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @PatchMapping("/{id}/verify")
    public ResponseEntity<Result> verifyResult(@PathVariable Long id) {
        return ResponseEntity.ok(resultService.verifyResult(id));
    }
}