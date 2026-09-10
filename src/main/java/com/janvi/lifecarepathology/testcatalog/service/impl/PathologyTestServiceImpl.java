package com.janvi.lifecarepathology.testcatalog.service;

import com.janvi.lifecarepathology.testcatalog.dto.PathologyTestRequest;
import com.janvi.lifecarepathology.testcatalog.dto.PathologyTestResponse;

import java.util.List;

public interface PathologyTestService {
    PathologyTestResponse createTest(PathologyTestRequest request);
    PathologyTestResponse getTestById(Long id);
    List<PathologyTestResponse> getAllTests();
    List<PathologyTestResponse> getActiveTests();
    PathologyTestResponse updateTest(Long id, PathologyTestRequest request);
    void deactivateTest(Long id);
}