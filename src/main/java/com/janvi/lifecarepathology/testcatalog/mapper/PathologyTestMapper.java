package com.janvi.lifecarepathology.testcatalog.mapper;

import com.janvi.lifecarepathology.testcatalog.dto.PathologyTestRequest;
import com.janvi.lifecarepathology.testcatalog.dto.PathologyTestResponse;
import com.janvi.lifecarepathology.testcatalog.entity.PathologyTest;
import org.springframework.stereotype.Component;

@Component
public class PathologyTestMapper {

    public PathologyTest toEntity(PathologyTestRequest request) {
        PathologyTest test = new PathologyTest();
        test.setTestName(request.getTestName());
        test.setTestCode(request.getTestCode());
        test.setDescription(request.getDescription());
        test.setPrice(request.getPrice());
        test.setSampleType(request.getSampleType());
        test.setNormalRange(request.getNormalRange());
        return test; // active defaults to true on the entity itself
    }

    public PathologyTestResponse toResponse(PathologyTest test) {
        PathologyTestResponse response = new PathologyTestResponse();
        response.setId(test.getId());
        response.setTestName(test.getTestName());
        response.setTestCode(test.getTestCode());
        response.setDescription(test.getDescription());
        response.setPrice(test.getPrice());
        response.setSampleType(test.getSampleType());
        response.setNormalRange(test.getNormalRange());
        response.setActive(test.getActive());
        return response;
    }
}