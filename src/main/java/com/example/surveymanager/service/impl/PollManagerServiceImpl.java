package com.example.surveymanager.service.impl;

import com.example.surveymanager.model.Poll;
import com.example.surveymanager.repository.PollRepo;
import com.example.surveymanager.service.PollManagerService;
import com.example.surveymanager.service.exceptions.PollIsNotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    public List<Poll> getPolls(Integer page, Integer pageSize, Map<String, Boolean> sortBy, Map<String, Object> filters) {
        Sort sort = Sort.unsorted();

        for (Map.Entry<String, Boolean> entry : sortBy.entrySet()) {
            String neededField = PollServiceUtils.getField(entry.getKey());

            if (neededField == null) {
                continue;
            }
            if (entry.getValue()) {
                sort = sort.and(Sort.by(neededField).ascending());
            } else {
                sort = sort.and(Sort.by(neededField).descending());
            }
        }


        Specification<Poll> specification = PollServiceUtils.getSpecifications(filters);

        return pollRepo.findAll(specification, PageRequest.of(page, pageSize, sort)).getContent();
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
