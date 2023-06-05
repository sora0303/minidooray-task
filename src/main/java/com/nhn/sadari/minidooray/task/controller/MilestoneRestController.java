package com.nhn.sadari.minidooray.task.controller;


import com.nhn.sadari.minidooray.task.domain.IdResponse;
import com.nhn.sadari.minidooray.task.domain.MilestoneRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.exception.ValidationFailedException;
import com.nhn.sadari.minidooray.task.service.MilestoneService;
import com.nhn.sadari.minidooray.task.service.TagService;
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
public class MilestoneRestController {

    private final MilestoneService milestoneService;

    //마일스톤 등록 /api/projects/{projectId}/milestones
    @PostMapping(value = "/{projectId}/milestones")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IdResponse> createMilestone(@PathVariable Long projectId, @RequestBody @Valid MilestoneRegisterRequest milestoneRegisterRequest,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = milestoneService.createMilestone(projectId, milestoneRegisterRequest);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.CREATED);
    }


    //마일스톤 수정 /api/projects/{projectId}/milestones/{milestoneId}
    @PutMapping(value = "/{projectId}/milestones/{milestoneId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdResponse> modifyMilestone(@PathVariable Long projectId, @PathVariable Long milestoneId, @RequestBody @Valid MilestoneRegisterRequest milestoneRegisterRequest,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = milestoneService.modifyMilestone(projectId, milestoneId, milestoneRegisterRequest);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }


    //마일스톤 삭제 DELETE /api/projects/{projectId}/milestones/{milestoneId}
    @DeleteMapping(value = "/{projectId}/milestones/{milestoneId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdResponse> deleteMilestone(@PathVariable Long projectId, @PathVariable Long milestoneId) {

        Long responseId = milestoneService.deleteMilestone(projectId, milestoneId);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }
}
