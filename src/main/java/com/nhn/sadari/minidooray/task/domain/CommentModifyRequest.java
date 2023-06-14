package com.nhn.sadari.minidooray.task.domain;

import com.nhn.sadari.minidooray.task.entity.Comment;
import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentModifyRequest {

    @NotNull
    @NotBlank
    String contents;

    @Builder
    public CommentModifyRequest(String contents) {
        this.contents = contents;
    }
}
