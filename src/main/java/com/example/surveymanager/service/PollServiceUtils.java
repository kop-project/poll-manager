package com.example.surveymanager.service;

import com.example.surveymanager.dto.PollRequestDTO;
import com.example.surveymanager.model.Poll;

public class PollServiceUtils {

    public static Poll convertDtoToModel(PollRequestDTO pollRequestDTO) {
        return Poll.builder().
                id(pollRequestDTO.getId())
                .name(pollRequestDTO.getName())
                .dtOpen(pollRequestDTO.getDtOpen())
                .dtClose(pollRequestDTO.getDtClose())
                .isActual(pollRequestDTO.getIsActual())
                .build();
    }
}
