package com.nhn.sadari.minidooray.task.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRegisterRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Long memberId;

}
