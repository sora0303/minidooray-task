package com.nhn.sadari.minidooray.task.controller;


import com.nhn.sadari.minidooray.task.domain.IdResponse;
import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.TaskModifyRequest;
import com.nhn.sadari.minidooray.task.domain.TaskRegisterRequest;
import com.nhn.sadari.minidooray.task.exception.ValidationFailedException;
import com.nhn.sadari.minidooray.task.service.TaskService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
@Slf4j
public class TaskRestController {

    private final TaskService taskService;

    //업무등록 /api/projects/{projectId}/tasks
    @PostMapping(value = "/{projectId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IdResponse> createTask(@PathVariable Long projectId, @RequestBody
    @Valid TaskRegisterRequest taskRegisterRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = taskService.createTask(projectId, taskRegisterRequest);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.CREATED);
    }


    //업무수정 /api/projects/{projectId}/tasks/{taskId}
    @PutMapping (value = "/{projectId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdResponse> modifyTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody
    @Valid TaskModifyRequest taskModifyRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = taskService.modifyTask(projectId, taskId, taskModifyRequest);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }


    //업무삭제 /api/projects/{projectId}/tasks/{taskId}
    @DeleteMapping(value = "/{projectId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdResponse> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {

        Long responseId = taskService.deleteTask(projectId, taskId);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }
}
