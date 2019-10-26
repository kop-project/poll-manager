package com.example.surveymanager.service.impl;

import com.example.surveymanager.model.Poll;
import com.example.surveymanager.repository.PollRepo;
import com.example.surveymanager.service.PollManagerService;
import com.example.surveymanager.service.exceptions.PollIsNotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PollManagerServiceImpl implements PollManagerService {

    private final PollRepo pollRepo;

    @Autowired
    public PollManagerServiceImpl(PollRepo pollRepo) {
        this.pollRepo = pollRepo;
    }

    @Override
    public void addPoll(Poll poll) {
        pollRepo.save(poll);
    }

    @Override
    public List<Poll> getPolls() {
        return pollRepo.findAll();
    }

    @Override
    public List<Poll> getPolls(Integer page, Integer pageSize, Map<String, Boolean> sortBy, List<Map<String, Object>> filters) {
        Sort sort = Sort.unsorted();
        return null;
    }

    @Override
    public Poll updatePoll(Poll poll) {
        pollRepo.findById(poll.getId()).orElseThrow(() -> new PollIsNotException("Опроса с таким Id не существует"));
        pollRepo.save(poll);
        return null;
    }

    @Override
    public void removePoll(Long id) {
        Poll poll = pollRepo.findById(id).orElseThrow(() -> new PollIsNotException("Опроса с таким Id не существует"));
        pollRepo.delete(poll);
    }
}
