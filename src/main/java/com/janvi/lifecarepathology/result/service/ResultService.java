package com.janvi.lifecarepathology.result.service;

import com.janvi.lifecarepathology.result.dto.ResultResponse;

import java.util.List;

public interface ResultService {
    ResultResponse enterResult(Long sampleId, String resultData, String remarks, String enteredBy);
    ResultResponse getResultById(Long id);
    List<ResultResponse> getAllResults();
    ResultResponse verifyResult(Long id);
}