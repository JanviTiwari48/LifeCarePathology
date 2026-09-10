package com.janvi.lifecarepathology.result.service.impl;

import com.janvi.lifecarepathology.result.entity.Result;
import com.janvi.lifecarepathology.result.repository.ResultRepository;
import com.janvi.lifecarepathology.result.service.ResultService;
import com.janvi.lifecarepathology.sample.entity.Sample;
import com.janvi.lifecarepathology.sample.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final SampleRepository sampleRepository;

    @Override
    public Result enterResult(Long sampleId, String resultData, String remarks, String enteredBy) {
        Sample sample = sampleRepository.findById(sampleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sample not found with id: " + sampleId));

        Result result = new Result();
        result.setSample(sample);
        result.setResultData(resultData);
        result.setRemarks(remarks);
        result.setEnteredBy(enteredBy);
        result.setEnteredAt(LocalDateTime.now());
        result.setVerified(false);

        return resultRepository.save(result);
    }

    @Override
    public Result getResultById(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with id: " + id));
    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public Result verifyResult(Long id) {
        Result result = getResultById(id);
        result.setVerified(true);
        return resultRepository.save(result);
    }
}