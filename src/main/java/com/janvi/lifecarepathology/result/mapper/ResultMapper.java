package com.janvi.lifecarepathology.result.mapper;

import com.janvi.lifecarepathology.result.dto.ResultResponse;
import com.janvi.lifecarepathology.result.entity.Result;
import org.springframework.stereotype.Component;

@Component
public class ResultMapper {

    public ResultResponse toResponse(Result result) {
        ResultResponse response = new ResultResponse();
        response.setId(result.getId());
        response.setSampleId(result.getSample().getId());
        response.setResultData(result.getResultData());
        response.setRemarks(result.getRemarks());
        response.setEnteredBy(result.getEnteredBy());
        response.setEnteredAt(result.getEnteredAt());
        response.setVerified(result.isVerified());
        return response;
    }
}