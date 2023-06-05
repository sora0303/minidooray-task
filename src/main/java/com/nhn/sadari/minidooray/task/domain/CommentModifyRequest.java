package com.nhn.sadari.minidooray.task.domain;

import com.nhn.sadari.minidooray.task.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CommentModifyRequest {

    String contents;

    @Builder
    public CommentModifyRequest(){}

}
