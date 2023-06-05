package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.CommentModifyRequest;
import com.nhn.sadari.minidooray.task.domain.CommentRegisterRequest;

public interface CommentService {

    Long createComment(Long taskId, CommentRegisterRequest commentRegisterRequest);

    Long modifyComment(Long taskId, Long commentId, CommentModifyRequest commentModifyRequest);

    Long deleteComment(Long taskId, Long commentId);

    }
