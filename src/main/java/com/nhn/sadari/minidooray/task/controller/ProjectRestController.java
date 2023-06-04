package com.nhn.sadari.minidooray.task.controller;

import com.nhn.sadari.minidooray.task.domain.ProjectRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectRegisterResponse;
import com.nhn.sadari.minidooray.task.exception.ValidationFailedException;
import com.nhn.sadari.minidooray.task.service.ProjectService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
@Slf4j
public class ProjectRestController {

    private final ProjectService projectService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = {"/", ""})
    public ResponseEntity<ProjectRegisterResponse> createProject(@RequestBody @Valid ProjectRegisterRequest projectRegisterRequest,
                                                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        Long projectId = projectService.createProject(projectRegisterRequest);
        ProjectRegisterResponse projectRegisterResponse = new ProjectRegisterResponse(projectId);

        return new ResponseEntity<>(projectRegisterResponse, HttpStatus.CREATED);
    }

}
