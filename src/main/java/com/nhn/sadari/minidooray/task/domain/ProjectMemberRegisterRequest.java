package com.nhn.sadari.minidooray.task.domain;

import com.nhn.sadari.minidooray.task.enumclass.ProjectMemberRoleType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberRegisterRequest {

    @NotNull
    Long memberId;

    @NotNull
    String memberName;

    @NotNull
    ProjectMemberRoleType role;
}
