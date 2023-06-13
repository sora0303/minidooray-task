package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.TagDto;
import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.entity.Tag;
import java.util.List;

public interface TagService {

    Long createTag(Long projectId, TagRegisterRequest tagRegisterRequest);

    Long modifyTag(Long projectId, Long tagId, TagRegisterRequest tagRegisterRequest);

    Long deleteTag(Long projectId, Long tagId);

    //프로젝트 아이디로 태그 조회 /api/projects/{projectId}/tags
    List<TagDto> getTagsByProjectId(Long projectId);

}
