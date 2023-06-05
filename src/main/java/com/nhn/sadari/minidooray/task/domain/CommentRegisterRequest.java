package com.nhn.sadari.minidooray.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRegisterRequest {

    Long writerId;

    String contents;
}
