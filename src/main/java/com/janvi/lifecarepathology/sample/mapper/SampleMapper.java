package com.janvi.lifecarepathology.sample.mapper;

import com.janvi.lifecarepathology.sample.dto.SampleResponse;
import com.janvi.lifecarepathology.sample.entity.Sample;
import org.springframework.stereotype.Component;

@Component
public class SampleMapper {

    public SampleResponse toResponse(Sample sample) {
        SampleResponse response = new SampleResponse();
        response.setId(sample.getId());
        response.setBookingId(sample.getBooking().getId()); // just the id — no full mapper call needed
        response.setSampleCode(sample.getSampleCode());
        response.setStatus(sample.getStatus());
        response.setCollectedAt(sample.getCollectedAt());
        response.setCollectedBy(sample.getCollectedBy());
        return response;
    }
}