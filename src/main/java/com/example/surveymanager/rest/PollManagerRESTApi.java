package com.example.surveymanager.rest;

import com.example.surveymanager.dto.PollRequestDTO;
import com.example.surveymanager.model.Poll;
import com.example.surveymanager.service.PollManagerService;
import com.example.surveymanager.service.PollServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

/**
 * Контроллер API менеджера опросов
 */
@RestController
@RequestMapping(value = "/api/v1/poll")
public class PollManagerRESTApi {

    private final PollManagerService pollManagerService;

    @Autowired
    public PollManagerRESTApi(PollManagerService pollManagerService) {
        this.pollManagerService = pollManagerService;
    }

    @PostMapping
    public ResponseEntity addPoll(@RequestBody PollRequestDTO pollRequestDTO) {
        Poll poll = PollServiceUtils.convertDtoToModel(pollRequestDTO);
        pollManagerService.removePoll(poll.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getPolls(
            @RequestParam(name = "page", required = false) @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false) @Min(1) Integer pageSize,
            @RequestParam(name = "sortBy", required = false) Map<String, Boolean> sortBy,
            @RequestParam(name = "filters", required = false) List<Map<String, Object>> filters) {

        if ((page == null || pageSize == null) && (sortBy == null || sortBy.isEmpty()) && (filters == null || filters.isEmpty())) {
            pollManagerService.getPolls(page, pageSize, sortBy, filters);
        } else {
            pollManagerService.getPolls();
        }
        return null;
    }

    @PutMapping
    public ResponseEntity<Poll> updatePoll(@RequestBody PollRequestDTO pollRequestDTO) {
        Poll poll = PollServiceUtils.convertDtoToModel(pollRequestDTO);
        pollManagerService.updatePoll(poll);
        return ResponseEntity.ok(pollManagerService.updatePoll(poll));
    }

    @DeleteMapping
    public ResponseEntity removePoll(@RequestParam Long id) {
        pollManagerService.removePoll(id);
        return ResponseEntity.noContent().build();
    }
}
