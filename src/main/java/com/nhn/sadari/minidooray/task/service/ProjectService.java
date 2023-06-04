package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.ProjectModifyRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectRegisterRequest;

public interface ProjectService {

    Long createProject(ProjectRegisterRequest projectRegisterRequest);
    void modifyProject(Long projectId, ProjectModifyRequest projectModifyRequest);
    void deleteProject(Long projectId);
}
