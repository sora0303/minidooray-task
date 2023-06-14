package com.nhn.sadari.minidooray.task.controller;


import com.nhn.sadari.minidooray.task.domain.CommonResponse;
import com.nhn.sadari.minidooray.task.domain.IdResponse;
import com.nhn.sadari.minidooray.task.domain.MilestoneDto;
import com.nhn.sadari.minidooray.task.domain.MilestoneRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.TagDto;
import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.exception.ValidationFailedException;
import com.nhn.sadari.minidooray.task.service.MilestoneService;
import com.nhn.sadari.minidooray.task.service.TagService;
import java.util.Collections;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public CommonResponse<IdResponse> createMilestone(@PathVariable Long projectId, @RequestBody @Valid MilestoneRegisterRequest milestoneRegisterRequest,
                                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = milestoneService.createMilestone(projectId, milestoneRegisterRequest);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(201)
            .resultMessage("마일스톤 등록 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();
    }


    //마일스톤 수정 /api/projects/{projectId}/milestones/{milestoneId}
    @PutMapping(value = "/{projectId}/milestones/{milestoneId}")
    public CommonResponse<IdResponse> modifyMilestone(@PathVariable Long projectId, @PathVariable Long milestoneId, @RequestBody @Valid MilestoneRegisterRequest milestoneRegisterRequest,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = milestoneService.modifyMilestone(projectId, milestoneId, milestoneRegisterRequest);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("마일스톤 수정 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();
    }


    //마일스톤 삭제 DELETE /api/projects/{projectId}/milestones/{milestoneId}
    @DeleteMapping(value = "/{projectId}/milestones/{milestoneId}")
    public CommonResponse<IdResponse> deleteMilestone(@PathVariable Long projectId, @PathVariable Long milestoneId) {

        Long responseId = milestoneService.deleteMilestone(projectId, milestoneId);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("마일스톤 삭제 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();
    }

    // 프로젝트 아이디로 마일스톤 조회 /api/projects/{projectId}/milestones
    @GetMapping(value = "/{projectId}/milestones")
    public CommonResponse<MilestoneDto> getMilestonesByProjectId(@PathVariable Long projectId) {

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("마일스톤 리스트 조회 성공")
            .build();

        return CommonResponse.<MilestoneDto>builder()
            .header(header)
            .result(milestoneService.getMilestonesByProjectId(projectId))
            .build();
    }

    //마일스톤 조회 /api/projects/{projectId}/milestones
    @GetMapping(value = "/{projectId}/milestones/{milestoneId}")
    public CommonResponse<MilestoneDto> getMilestoneById(@PathVariable Long milestoneId) {

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("마일스톤 조회 성공")
            .build();

        return CommonResponse.<MilestoneDto>builder()
            .header(header)
            .result(Collections.singletonList(milestoneService.getMilestoneById(milestoneId)))
            .build();
    }
}
