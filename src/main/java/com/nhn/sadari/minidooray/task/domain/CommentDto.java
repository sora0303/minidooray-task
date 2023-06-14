package com.nhn.sadari.minidooray.task.domain;

import com.nhn.sadari.minidooray.task.entity.Task;
import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CommentDto {

    Long id;

    Long writerId;

    LocalDateTime createdAt;

    String contents;

    String writerName;

    @Builder
    public CommentDto(Long id, Long writerId, LocalDateTime createdAt, String contents) {
        this.id = id;
        this.writerId = writerId;
        this.createdAt = createdAt;
        this.contents = contents;
    }




}
