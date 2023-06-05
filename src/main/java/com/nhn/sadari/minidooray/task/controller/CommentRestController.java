package com.nhn.sadari.minidooray.task.controller;


import com.nhn.sadari.minidooray.task.domain.CommentModifyRequest;
import com.nhn.sadari.minidooray.task.domain.CommentRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.IdResponse;
import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.exception.ValidationFailedException;
import com.nhn.sadari.minidooray.task.service.CommentService;
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
@RequestMapping("/api/tasks")
@Slf4j
public class CommentRestController {

    private final CommentService commentService;

    //댓글 등록 /api/tasks/{tasksId}/comments
    @PostMapping(value = "/{taskId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IdResponse> createComment(@PathVariable Long taskId,
                                                    @RequestBody @Valid CommentRegisterRequest commentRegisterRequest,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = commentService.createComment(taskId, commentRegisterRequest);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.CREATED);
    }


    //댓글 수정 /api/tasks/{tasksId}/comments/{commentId}
    @PutMapping(value = "/{taskId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IdResponse> modifyComment(@PathVariable Long taskId, @PathVariable Long commentId, @RequestBody @Valid
    CommentModifyRequest commentModifyRequest,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = commentService.modifyComment(taskId, commentId, commentModifyRequest);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }

    //댓글 삭제 /api/tasks/{tasksId}/comments/{commentId}
    @DeleteMapping(value = "/{taskId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdResponse> deleteComment(@PathVariable Long taskId, @PathVariable Long commentId) {

        Long responseId = commentService.deleteComment(taskId, commentId);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }
}
