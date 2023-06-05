package com.nhn.sadari.minidooray.task.domain;

import com.nhn.sadari.minidooray.task.enumclass.ProjectMemberRoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberRegisterRequest {

    Long memberId;

    String memberName;

    ProjectMemberRoleType role;
}
