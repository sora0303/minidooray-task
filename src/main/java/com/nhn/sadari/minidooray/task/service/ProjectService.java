package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.ProjectModifyRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectRegisterRequest;

public interface ProjectService {

    Long createProject(ProjectRegisterRequest projectRegisterRequest);
    Long modifyProject(Long projectId, ProjectModifyRequest projectModifyRequest);
    Long deleteProject(Long projectId);
}
