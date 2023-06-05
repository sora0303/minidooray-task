package com.nhn.sadari.minidooray.task.controller;


import com.nhn.sadari.minidooray.task.domain.IdResponse;
import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.entity.Tag;
import com.nhn.sadari.minidooray.task.exception.TagNotFoundException;
import com.nhn.sadari.minidooray.task.exception.ValidationFailedException;
import com.nhn.sadari.minidooray.task.repository.TagRepository;
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
public class TagRestController {

    private final TagService tagService;

    //태그 등록 /api/projects/{projectId}/tags
    @PostMapping(value = "/{projectId}/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IdResponse> createTag(@PathVariable Long projectId, @RequestBody @Valid TagRegisterRequest tagRegisterRequest,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = tagService.createTag(projectId, tagRegisterRequest);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.CREATED);
    }


    //태그 수정 /api/projects/{projectId}/tags/{tagId}
    @PutMapping(value = "/{projectId}/tags/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdResponse> modifyTag(@PathVariable Long projectId, @PathVariable Long tagId, @RequestBody @Valid TagRegisterRequest tagRegisterRequest,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = tagService.modifyTag(projectId, tagId, tagRegisterRequest);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }


    //태그 삭제 DELETE /api/projects/{projectId}/tags/{tagId}
    @DeleteMapping(value = "/{projectId}/tags/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdResponse> deleteTag(@PathVariable Long projectId, @PathVariable Long tagId) {

        Long responseId = tagService.deleteTag(projectId, tagId);
        IdResponse idResponse = new IdResponse(responseId);

        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }
}
