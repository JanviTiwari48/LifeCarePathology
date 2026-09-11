package com.janvi.lifecarepathology.sample.service.impl;

import com.janvi.lifecarepathology.booking.entity.Booking;
import com.janvi.lifecarepathology.booking.repository.BookingRepository;
import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
import com.janvi.lifecarepathology.sample.dto.SampleResponse;
import com.janvi.lifecarepathology.sample.entity.Sample;
import com.janvi.lifecarepathology.sample.entity.SampleStatus;
import com.janvi.lifecarepathology.sample.mapper.SampleMapper;
import com.janvi.lifecarepathology.sample.repository.SampleRepository;
import com.janvi.lifecarepathology.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;
    private final BookingRepository bookingRepository;
    private final SampleMapper sampleMapper;

    @Override
    public SampleResponse collectSample(Long bookingId, String sampleCode, String collectedBy) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        Sample sample = new Sample();
        sample.setBooking(booking);
        sample.setSampleCode(sampleCode);
        sample.setStatus(SampleStatus.COLLECTED);
        sample.setCollectedAt(LocalDateTime.now());
        sample.setCollectedBy(collectedBy);

        Sample saved = sampleRepository.save(sample);
        return sampleMapper.toResponse(saved);
    }

    @Override
    public SampleResponse getSampleById(Long id) {
        Sample sample = sampleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sample not found with id: " + id));
        return sampleMapper.toResponse(sample);
    }

    @Override
    public List<SampleResponse> getAllSamples() {
        return sampleRepository.findAll().stream()
                .map(sampleMapper::toResponse)
                .toList();
    }

    @Override
    public SampleResponse updateSampleStatus(Long id, String status) {
        Sample sample = sampleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sample not found with id: " + id));
        sample.setStatus(SampleStatus.valueOf(status.toUpperCase()));
        Sample saved = sampleRepository.save(sample);
        return sampleMapper.toResponse(saved);
    }
}