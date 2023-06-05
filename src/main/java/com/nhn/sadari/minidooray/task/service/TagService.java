package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.entity.Tag;

public interface TagService {

    Long createTag(Long projectId, TagRegisterRequest tagRegisterRequest);

    Long modifyTag(Long projectId, Long tagId, TagRegisterRequest tagRegisterRequest);

    Long deleteTag(Long projectId, Long tagId);

}
