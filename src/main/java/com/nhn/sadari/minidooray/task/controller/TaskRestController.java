package com.nhn.sadari.minidooray.task.controller;


import com.nhn.sadari.minidooray.task.domain.CommonResponse;
import com.nhn.sadari.minidooray.task.domain.IdResponse;
import com.nhn.sadari.minidooray.task.domain.TaskDto;
import com.nhn.sadari.minidooray.task.domain.TaskModifyRequest;
import com.nhn.sadari.minidooray.task.domain.TaskRegisterRequest;
import com.nhn.sadari.minidooray.task.exception.ValidationFailedException;
import com.nhn.sadari.minidooray.task.service.TaskService;
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
public class TaskRestController {

    private final TaskService taskService;

    //업무등록 /api/projects/{projectId}/tasks
    @PostMapping(value = "/{projectId}/tasks")
    public CommonResponse<IdResponse> createTask(@PathVariable Long projectId, @RequestBody
    @Valid TaskRegisterRequest taskRegisterRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = taskService.createTask(projectId, taskRegisterRequest);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(201)
            .resultMessage("업무 등록 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();
    }


    //업무수정 /api/projects/{projectId}/tasks/{taskId}
    @PutMapping (value = "/{projectId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<IdResponse> modifyTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody
    @Valid TaskModifyRequest taskModifyRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = taskService.modifyTask(projectId, taskId, taskModifyRequest);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("업무 수정 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();
    }


    //업무삭제 /api/projects/{projectId}/tasks/{taskId}
    @DeleteMapping(value = "/{projectId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<IdResponse> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {

        Long responseId = taskService.deleteTask(projectId, taskId);

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("업무 삭제 성공")
            .build();

        return CommonResponse.<IdResponse>builder()
            .header(header)
            .result(Collections.singletonList(new IdResponse(responseId)))
            .build();
    }

    //프로젝트 아이디로 업무 리스트 조회 /api/projects/{projectId}/tasks
    @GetMapping(value = "/{projectId}/tasks")
    public CommonResponse<TaskDto> getTasksByProjectId(@PathVariable Long projectId) {

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("프로젝트 아이디로 업무 리스트 조회 성공")
            .build();

        return CommonResponse.<TaskDto>builder()
            .header(header)
            .result(taskService.getTasksByProjectId(projectId))
            .build();
    }

    //업무 아이디로 업무 조회 /api/projects/{projectId}/tasks/{taskId}
    @GetMapping(value = "/{projectId}/tasks/{taskId}")
    public CommonResponse<TaskDto> getTaskBytaskId(@PathVariable Long taskId) {

        CommonResponse.Header header = CommonResponse.Header.builder()
            .isSuccessful(true)
            .resultCode(200)
            .resultMessage("업무 아이디로 업무 조회 성공")
            .build();

        return CommonResponse.<TaskDto>builder()
            .header(header)
            .result(Collections.singletonList(taskService.getTaskByTaskId(taskId)))
            .build();
    }
}
