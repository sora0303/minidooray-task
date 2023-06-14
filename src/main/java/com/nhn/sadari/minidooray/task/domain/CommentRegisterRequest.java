package com.nhn.sadari.minidooray.task.domain;

import com.sun.istack.NotNull;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CommentRegisterRequest {

    @NotNull
    Long writerId;

    @NotNull
    @NotBlank
    String contents;

    @Builder
    public CommentRegisterRequest(Long writerId, String contents) {
        this.writerId = writerId;
        this.contents = contents;
    }
}
