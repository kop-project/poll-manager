package com.example.surveymanager.rest;

import com.example.surveymanager.dto.PollRequestDTO;
import com.example.surveymanager.model.Poll;
import com.example.surveymanager.service.ConverterDtoToModel;
import com.example.surveymanager.service.PollManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.Map;

/**
 * Контроллер API менеджера опросов
 */
@RestController
@RequestMapping(value = "/api/v1/poll/")
@Api(value = "Poll Manager", description = "Api для работы с опросами")
public class PollManagerRESTApi {

    private final PollManagerService pollManagerService;

    @Autowired
    public PollManagerRESTApi(PollManagerService pollManagerService) {
        this.pollManagerService = pollManagerService;
    }

    @PostMapping
    @ApiOperation(value = "Создание опроса", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Опрос успешно создан"),
    })
    public ResponseEntity addPoll(@RequestBody PollRequestDTO pollRequestDTO) {
        Poll poll = ConverterDtoToModel.convertDtoToModel(pollRequestDTO);
        pollManagerService.removePoll(poll.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{idPoll}")
    @ApiOperation(value = "Получение опроса", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Опрос успешно найден"),
            @ApiResponse(code = 500, message = "Опрос с таким id не существует"),
    })
    public ResponseEntity getPolls(
            @RequestParam(name = "page", required = false) @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false) @Min(1) Integer pageSize,
            @RequestParam(name = "sortBy", required = false) Map<String, Boolean> sortBy,
            @RequestParam(name = "filters", required = false) Map<String, Object> filters,
            @PathVariable Long idPoll
    ) {
        if ((page == null || pageSize == null) && (sortBy == null || sortBy.isEmpty()) && (filters == null || filters.isEmpty())) {
            if (idPoll == null) {
                return ResponseEntity.ok(pollManagerService.getPolls());
            } else {
                return ResponseEntity.ok(pollManagerService.getPoll(idPoll));
            }
        } else {
            return ResponseEntity.ok(pollManagerService.getPolls(page, pageSize, sortBy, filters));
        }
    }

    @PutMapping
    @ApiOperation(value = "Обновление опроса", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Опрос успешно создан"),
            @ApiResponse(code = 500, message = "Опрос с таким id не существует")
    })
    public ResponseEntity<Poll> updatePoll(@RequestBody PollRequestDTO pollRequestDTO) {
        Poll poll = ConverterDtoToModel.convertDtoToModel(pollRequestDTO);
        pollManagerService.updatePoll(poll);
        return ResponseEntity.ok(pollManagerService.updatePoll(poll));
    }

    @DeleteMapping
    @ApiOperation(value = "Удаление опроса", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Опрос успешно удален"),
            @ApiResponse(code = 500, message = "Опрос с таким id не существует")
    })
    public ResponseEntity removePoll(@RequestParam Long id) {
        pollManagerService.removePoll(id);
        return ResponseEntity.noContent().build();
    }
}
