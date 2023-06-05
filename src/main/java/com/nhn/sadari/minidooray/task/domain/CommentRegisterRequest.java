package com.nhn.sadari.minidooray.task.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CommentRegisterRequest {

    Long writerId;

    String contents;

    @Builder
    public CommentRegisterRequest(Long writerId, String contents) {
        this.writerId = writerId;
        this.contents = contents;
    }
}
