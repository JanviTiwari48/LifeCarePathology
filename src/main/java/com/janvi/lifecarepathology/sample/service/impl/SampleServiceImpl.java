package com.janvi.lifecarepathology.sample.service.impl;

import com.janvi.lifecarepathology.booking.entity.Booking;
import com.janvi.lifecarepathology.booking.repository.BookingRepository;
import com.janvi.lifecarepathology.sample.entity.Sample;
import com.janvi.lifecarepathology.sample.entity.SampleStatus;
import com.janvi.lifecarepathology.sample.repository.SampleRepository;
import com.janvi.lifecarepathology.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import com.janvi.lifecarepathology.common.exception.ResourceNotFoundException;
@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;
    private final BookingRepository bookingRepository;

    @Override
    public Sample collectSample(Long bookingId, String sampleCode, String collectedBy) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        Sample sample = new Sample();
        sample.setBooking(booking);
        sample.setSampleCode(sampleCode);
        sample.setStatus(SampleStatus.COLLECTED);
        sample.setCollectedAt(LocalDateTime.now());
        sample.setCollectedBy(collectedBy);

        return sampleRepository.save(sample);
    }

    @Override
    public Sample getSampleById(Long id) {
        return sampleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sample not found with id: " + id));
    }

    @Override
    public List<Sample> getAllSamples() {
        return sampleRepository.findAll();
    }

    @Override
    public Sample updateSampleStatus(Long id, String status) {
        Sample sample = getSampleById(id);
        sample.setStatus(SampleStatus.valueOf(status.toUpperCase()));
        return sampleRepository.save(sample);
    }
}