package com.janvi.lifecarepathology.sample.service;

import com.janvi.lifecarepathology.sample.entity.Sample;

import java.util.List;

public interface SampleService {
    Sample collectSample(Long bookingId, String sampleCode, String collectedBy);
    Sample getSampleById(Long id);
    List<Sample> getAllSamples();
    Sample updateSampleStatus(Long id, String status);
}