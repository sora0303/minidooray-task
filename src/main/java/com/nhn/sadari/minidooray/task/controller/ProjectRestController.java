package com.nhn.sadari.minidooray.task.controller;

import com.nhn.sadari.minidooray.task.domain.ProjectModifyRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.IdResponse;
import com.nhn.sadari.minidooray.task.exception.ValidationFailedException;
import com.nhn.sadari.minidooray.task.service.ProjectService;
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
public class ProjectRestController {

    private final ProjectService projectService;

    //프로젝트 등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = {"/", ""})
    public ResponseEntity<IdResponse> createProject(@RequestBody @Valid ProjectRegisterRequest projectRegisterRequest,
                                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = projectService.createProject(projectRegisterRequest);
        IdResponse response = new IdResponse(responseId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //프로젝트 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{projectId}")
    public ResponseEntity<IdResponse> modifyProject(@PathVariable("projectId") Long projectId, @RequestBody @Valid ProjectModifyRequest projectModifyRequest,
                                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = projectService.modifyProject(projectId, projectModifyRequest);
        IdResponse response = new IdResponse(projectId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //프로젝트 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity<IdResponse> deleteProject(@PathVariable("projectId") Long projectId) {

        Long responseId = projectService.deleteProject(projectId);
        IdResponse response = new IdResponse(projectId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //프로젝트 멤버 등록



    //프로젝트 멤버 수정

    //프로젝트 멤버 삭제

}
