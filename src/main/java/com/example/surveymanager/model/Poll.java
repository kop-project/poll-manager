package com.example.surveymanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "POLL")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Poll {

    /**
     * id опроса
     */
    @Id
    @Column(name = "POLL_ID")
    private Long id;

    /**
     * Имя справочника
    */
    @Column(name = "POLL_NAME")
    private String name;

    /**
     * Дата начала
     */
    @Column(name = "DT_OPEN")
    private Date dtOpen;

    /**
     * Дата окончания
     */
    @Column(name = "DT_CLOSE")
    private Date dtClose;

    /**
     * Актуальность
     */
    @Column(name = "IS_ACTUAL")
    private Boolean isActual;

}
