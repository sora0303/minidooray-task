package com.nhn.sadari.minidooray.task.controller;

import com.fasterxml.classmate.AnnotationOverrides;
import com.nhn.sadari.minidooray.task.domain.CommonResponse;
import com.nhn.sadari.minidooray.task.domain.ProjectDto;
import com.nhn.sadari.minidooray.task.domain.ProjectMemberModifyRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectMemberRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectModifyRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.IdResponse;
import com.nhn.sadari.minidooray.task.exception.ValidationFailedException;
import com.nhn.sadari.minidooray.task.service.ProjectService;
import java.util.Collections;
import java.util.List;
import javax.persistence.Id;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
public class ProjectRestController {

    private final ProjectService projectService;

    //프로젝트 등록 /api/projects
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping(value = {"/", ""})
//    public ResponseEntity<IdResponse> createProject(@RequestBody @Valid ProjectRegisterRequest projectRegisterRequest,
//                                                    BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            throw new ValidationFailedException(bindingResult);
//        }
//
//        Long responseId = projectService.createProject(projectRegisterRequest);
//        IdResponse response = new IdResponse(responseId);
//
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }


    @PostMapping(value = {"/", ""})
    public CommonResponse<IdResponse> createProject(@RequestBody @Valid ProjectRegisterRequest projectRegisterRequest,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = projectService.createProject(projectRegisterRequest);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(201)
            .resultMessage("프로젝트 등록 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();
    }


    //프로젝트 수정 /api/projects/{projectId}
    @PutMapping(value = "/{projectId}")
    public CommonResponse<IdResponse> modifyProject(@PathVariable("projectId") Long projectId,
                                                    @RequestBody @Valid ProjectModifyRequest projectModifyRequest,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = projectService.modifyProject(projectId, projectModifyRequest);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("프로젝트 수정 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();
    }

    //프로젝트 삭제 /api/projects/{projectId}
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{projectId}")
    public CommonResponse<IdResponse> deleteProject(@PathVariable("projectId") Long projectId) {

        Long responseId = projectService.deleteProject(projectId);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("프로젝트 삭제 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();
    }

    //프로젝트 멤버 등록 /api/projects/{projectId}/members
    @PostMapping(value = "/{projectId}/members")
    public CommonResponse<IdResponse> createProjectMember(@PathVariable long projectId, @RequestBody
    @Valid ProjectMemberRegisterRequest projectMemberRegisterRequest,
                                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = projectService.createProjectMember(projectId, projectMemberRegisterRequest);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(201)
            .resultMessage("프로젝트 멤버 등록 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();

    }


    //프로젝트 멤버 수정 /api/projects/{projectId}/members/{memberId}
    @PutMapping(value = "/{projectId}/members/{memberId}")
    public CommonResponse<IdResponse> modifyProjectMember(@PathVariable("projectId") Long projectId, @PathVariable Long memberId,
                                                          @RequestBody @Valid ProjectMemberModifyRequest projectMemberModifyRequest,
                                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = projectService.modifyProjectMember(projectId, memberId, projectMemberModifyRequest);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("프로젝트 멤버 수정 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();

    }

    //프로젝트 멤버 삭제 /api/projects/{projectId}/members/{memberId}
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{projectId}/members/{memberId}")
    public CommonResponse<IdResponse> deleteProjectMember(@PathVariable("projectId") Long projectId, @PathVariable Long memberId) {

        Long responseId = projectService.deleteProjectMember(projectId, memberId);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("프로젝트 멤버 삭제 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();

    }

    //프로젝트 아이디로 프로젝트 조회 /api/projects/{projectId}
    @GetMapping(value = "/{projectId}")
    public CommonResponse<ProjectDto> getProjectByProjectId(@PathVariable("projectId") Long projectId) {

        ProjectDto projectDto = projectService.getProjectByProjectId(projectId);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("프로젝트 아이디로 프로젝트 조회")
            .build();

        return CommonResponse.<ProjectDto>builder()
            .header(header)
            .result(Collections.singletonList(projectDto))
            .build();
    }

    //멤버 아이디로 프로젝트 리스트 조회 /api/projects/members/{memberId}
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/members/{memberId}")
    public CommonResponse<ProjectDto> getProjectsByMemberId(@PathVariable Long memberId) {

        List<ProjectDto> projectDtos = projectService.getProjectsByMemberId(memberId);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("멤버 아이디로 프로젝트 리스트 조회")
            .build();

        return CommonResponse.<ProjectDto>builder()
            .header(header)
            .result(projectDtos)
            .build();
    }

    //멤버 아이디로 프로젝트 리스트 조회 /api/projects/members/{memberId}
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(value = "/members/{memberId}")
//    public ResponseEntity<Page<ProjectDto>> getProjectsByMemberId(@PathVariable Long memberId) {
//
//        Page<ProjectDto> projectDtos = projectService.getProjectsByMemberId(memberId);
//
//        return new ResponseEntity<>(projectDtos, HttpStatus.OK);
//    }

}
