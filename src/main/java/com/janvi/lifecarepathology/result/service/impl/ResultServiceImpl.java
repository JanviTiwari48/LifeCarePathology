package com.janvi.lifecarepathology.result.service.impl;

import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
import com.janvi.lifecarepathology.result.dto.ResultResponse;
import com.janvi.lifecarepathology.result.entity.Result;
import com.janvi.lifecarepathology.result.mapper.ResultMapper;
import com.janvi.lifecarepathology.result.repository.ResultRepository;
import com.janvi.lifecarepathology.result.service.ResultService;
import com.janvi.lifecarepathology.sample.entity.Sample;
import com.janvi.lifecarepathology.sample.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final SampleRepository sampleRepository;
    private final ResultMapper resultMapper;

    @Override
    public ResultResponse enterResult(Long sampleId, String resultData, String remarks, String enteredBy) {
        Sample sample = sampleRepository.findById(sampleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sample not found with id: " + sampleId));

        Result result = new Result();
        result.setSample(sample);
        result.setResultData(resultData);
        result.setRemarks(remarks);
        result.setEnteredBy(enteredBy);
        result.setEnteredAt(LocalDateTime.now());
        result.setVerified(false);

        Result saved = resultRepository.save(result);
        return resultMapper.toResponse(saved);
    }

    @Override
    public ResultResponse getResultById(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with id: " + id));
        return resultMapper.toResponse(result);
    }

    @Override
    public List<ResultResponse> getAllResults() {
        return resultRepository.findAll().stream()
                .map(resultMapper::toResponse)
                .toList();
    }

    @Override
    public ResultResponse verifyResult(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with id: " + id));
        result.setVerified(true);
        Result saved = resultRepository.save(result);
        return resultMapper.toResponse(saved);
    }
}