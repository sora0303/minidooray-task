package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.ProjectModifyRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectRegisterRequest;
import com.nhn.sadari.minidooray.task.entity.Project;
import com.nhn.sadari.minidooray.task.entity.ProjectMember;
import com.nhn.sadari.minidooray.task.entity.ProjectStatus;
import com.nhn.sadari.minidooray.task.enumclass.ProjectStatusType;
import com.nhn.sadari.minidooray.task.repository.ProjectMemberRepository;
import com.nhn.sadari.minidooray.task.repository.ProjectRepository;
import com.nhn.sadari.minidooray.task.repository.ProjectStatusRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projectService")
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository projectStatusRepository;

    private final ProjectMemberRepository projectMemberRepository;

    @Override
    @Transactional
    public Long createProject(ProjectRegisterRequest projectRegisterRequest) {

        Project project = new Project();
        project.setName(projectRegisterRequest.getName());
        project.setDescription(projectRegisterRequest.getDescription());

        ProjectStatus projectStatus = projectStatusRepository.findByStatus(ProjectStatusType.활성);
        project.setProjectStatus(projectStatus);

        ProjectMember projectMember = new ProjectMember();


        projectRepository.save(project);

        return project.getId();
    }

    @Override
    public void modifyProject(Long projectId, ProjectModifyRequest projectModifyRequest) {

        Project project = projectRepository.findById(projectId).orElseThrow();

        ProjectStatusType projectStatusType = projectModifyRequest.getStatus();
        ProjectStatus projectStatus = projectStatusRepository.findByStatus(projectStatusType);
        project.setProjectStatus(projectStatus);

    }

    @Override
    public void deleteProject(Long projectId) {

    }
}
