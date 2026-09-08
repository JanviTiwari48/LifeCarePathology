package com.janvi.lifecarepathology.result.service;

import com.janvi.lifecarepathology.result.entity.Result;

import java.util.List;

public interface ResultService {
    Result enterResult(Long sampleId, String resultData, String remarks, String enteredBy);
    Result getResultById(Long id);
    List<Result> getAllResults();
    Result verifyResult(Long id);
}