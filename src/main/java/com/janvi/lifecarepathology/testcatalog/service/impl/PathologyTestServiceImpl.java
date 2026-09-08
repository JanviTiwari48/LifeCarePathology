package com.janvi.lifecarepathology.testcatalog.service.impl;

import com.janvi.lifecarepathology.testcatalog.entity.PathologyTest;
import com.janvi.lifecarepathology.testcatalog.repository.PathologyTestRepository;
import com.janvi.lifecarepathology.testcatalog.service.PathologyTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PathologyTestServiceImpl implements PathologyTestService {

    private final PathologyTestRepository testRepository;

    @Override
    public PathologyTest createTest(PathologyTest test) {
        return testRepository.save(test);
    }

    @Override
    public PathologyTest getTestById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Test not found with id: " + id));
    }

    @Override
    public List<PathologyTest> getAllTests() {
        return testRepository.findAll();
    }

    @Override
    public List<PathologyTest> getActiveTests() {
        return testRepository.findByActiveTrue();
    }

    @Override
    public PathologyTest updateTest(Long id, PathologyTest updatedTest) {
        PathologyTest existing = getTestById(id);
        existing.setTestName(updatedTest.getTestName());
        existing.setTestCode(updatedTest.getTestCode());
        existing.setDescription(updatedTest.getDescription());
        existing.setPrice(updatedTest.getPrice());
        existing.setSampleType(updatedTest.getSampleType());
        existing.setNormalRange(updatedTest.getNormalRange());
        return testRepository.save(existing);
    }

    @Override
    public void deactivateTest(Long id) {
        PathologyTest existing = getTestById(id);
        existing.setActive(false);
        testRepository.save(existing);
    }
}