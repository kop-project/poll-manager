package com.example.surveymanager.service;

import com.example.surveymanager.model.Poll;

import java.util.List;
import java.util.Map;

/**
 * Сервис для работы с опросами
 */
public interface PollManagerService {

    /**
     * Добавить опрос
     *
     * @param poll опрос
     */
    void addPoll(Poll poll);

    /**
     * Вернуть опросы
     *
     * @return список опросов
     */
    List<Poll> getPolls();

    /**
     * Вернуть опрос по id
     *
     * @param id id опроса
     * @return опросов
     */
    Poll getPoll(Long id);

    /**
     * Вернуть опросы
     *
     * @return список опросов
     */
    List<Poll> getPolls(Integer page, Integer pageSize, Map<String, Boolean> sortBy, Map<String, Object> filters);


    /**
     * Обновить опрос
     *
     * @param poll опрос
     * @return опрос
     */
    Poll updatePoll(Poll poll);


    /**
     * Удалить опрос
     *
     * @param id id опроса
     */
    void removePoll(Long id);
}
