package com.janvi.lifecarepathology.testcatalog.service;

import com.janvi.lifecarepathology.testcatalog.entity.PathologyTest;

import java.util.List;

public interface PathologyTestService {
    PathologyTest createTest(PathologyTest test);
    PathologyTest getTestById(Long id);
    List<PathologyTest> getAllTests();
    List<PathologyTest> getActiveTests();
    PathologyTest updateTest(Long id, PathologyTest updatedTest);
    void deactivateTest(Long id);
}