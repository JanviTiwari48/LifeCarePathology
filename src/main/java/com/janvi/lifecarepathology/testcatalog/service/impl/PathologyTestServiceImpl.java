package com.janvi.lifecarepathology.testcatalog.service.impl;

import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
import com.janvi.lifecarepathology.testcatalog.dto.PathologyTestRequest;
import com.janvi.lifecarepathology.testcatalog.dto.PathologyTestResponse;
import com.janvi.lifecarepathology.testcatalog.entity.PathologyTest;
import com.janvi.lifecarepathology.testcatalog.mapper.PathologyTestMapper;
import com.janvi.lifecarepathology.testcatalog.repository.PathologyTestRepository;
import com.janvi.lifecarepathology.testcatalog.service.PathologyTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PathologyTestServiceImpl implements PathologyTestService {

    private final PathologyTestRepository testRepository;
    private final PathologyTestMapper testMapper;

    @Override
    public PathologyTestResponse createTest(PathologyTestRequest request) {
        PathologyTest test = testMapper.toEntity(request);
        PathologyTest saved = testRepository.save(test);
        return testMapper.toResponse(saved);
    }

    @Override
    public PathologyTestResponse getTestById(Long id) {
        PathologyTest test = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test not found with id: " + id));
        return testMapper.toResponse(test);
    }

    @Override
    public List<PathologyTestResponse> getAllTests() {
        return testRepository.findAll().stream()
                .map(testMapper::toResponse)
                .toList();
    }

    @Override
    public List<PathologyTestResponse> getActiveTests() {
        return testRepository.findByActiveTrue().stream()
                .map(testMapper::toResponse)
                .toList();
    }

    @Override
    public PathologyTestResponse updateTest(Long id, PathologyTestRequest request) {
        PathologyTest existing = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test not found with id: " + id));
        existing.setTestName(request.getTestName());
        existing.setTestCode(request.getTestCode());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setSampleType(request.getSampleType());
        existing.setNormalRange(request.getNormalRange());
        PathologyTest saved = testRepository.save(existing);
        return testMapper.toResponse(saved);
    }

    @Override
    public void deactivateTest(Long id) {
        PathologyTest existing = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test not found with id: " + id));
        existing.setActive(false);
        testRepository.save(existing);
    }
}