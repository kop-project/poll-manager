package com.example.surveymanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Poll {

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
