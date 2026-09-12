package com.janvi.lifecarepathology.result.service.impl;

import com.janvi.lifecarepathology.booking.entity.BookingStatus;
import com.janvi.lifecarepathology.booking.service.BookingService;
import com.janvi.lifecarepathology.common.exception.BusinessRuleException;
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
    private final BookingService bookingService; // NEW

    @Override
    public ResultResponse enterResult(Long sampleId, String resultData, String remarks, String enteredBy) {
        Sample sample = sampleRepository.findById(sampleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sample not found with id: " + sampleId));

        // NEW — guard: can't enter a result until the booking reflects SAMPLE_COLLECTED
        if (sample.getBooking().getStatus() != BookingStatus.SAMPLE_COLLECTED) {
            throw new BusinessRuleException(
                    "Cannot enter result: booking must be SAMPLE_COLLECTED first (current status: "
                            + sample.getBooking().getStatus() + ")");
        }

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

        // NEW — advance the booking now that the result is verified (not merely entered)
        Long bookingId = result.getSample().getBooking().getId();
        bookingService.updateBookingStatus(bookingId, BookingStatus.RESULT_ENTERED);

        return resultMapper.toResponse(saved);
    }
}