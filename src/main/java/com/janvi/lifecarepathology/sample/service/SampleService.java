package com.janvi.lifecarepathology.sample.service;

import com.janvi.lifecarepathology.sample.dto.SampleResponse;

import java.util.List;

public interface SampleService {
    SampleResponse collectSample(Long bookingId, String sampleCode, String collectedBy);
    SampleResponse getSampleById(Long id);
    List<SampleResponse> getAllSamples();
    SampleResponse updateSampleStatus(Long id, String status);
}