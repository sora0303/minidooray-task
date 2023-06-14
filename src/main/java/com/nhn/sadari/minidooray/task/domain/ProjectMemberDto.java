package com.nhn.sadari.minidooray.task.domain;

import com.nhn.sadari.minidooray.task.enumclass.ProjectMemberRoleType;

public interface ProjectMemberDto {


    Long getPk_memberId();

    String getMemberName();

    ProjectMemberRoleType getRole();
}
