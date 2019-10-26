package com.example.surveymanager.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Тело запроса опроса
 */
@Data
@Builder
public class PollRequestDTO {

    /**
     * id опроса
     */
    private Long id;

    /**
     * Имя справочника
     */
    private String name;

    /**
     * Дата начала
     */
    private Date dtOpen;

    /**
     * Дата окончания
     */
    private Date dtClose;

    /**
     * Актуальность
     */
    private Boolean isActual;

}
