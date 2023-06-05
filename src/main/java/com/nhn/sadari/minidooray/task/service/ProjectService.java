package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.ProjectDto;
import com.nhn.sadari.minidooray.task.domain.ProjectMemberModifyRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectMemberRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectModifyRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectRegisterRequest;
import com.nhn.sadari.minidooray.task.entity.Project;
import com.nhn.sadari.minidooray.task.entity.ProjectMember;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectService {

    Long createProject(ProjectRegisterRequest projectRegisterRequest);

    Long modifyProject(Long projectId, ProjectModifyRequest projectModifyRequest);

    Long deleteProject(Long projectId);

    Long createProjectMember(Long projectId, ProjectMemberRegisterRequest projectMemberRegisterRequest);

    Long modifyProjectMember(Long projectId, Long memberId, ProjectMemberModifyRequest projectMemberModifyRequest);

    Long deleteProjectMember(Long projectId, Long memberId);

    ProjectDto getProjectById(Long projectId);
}
