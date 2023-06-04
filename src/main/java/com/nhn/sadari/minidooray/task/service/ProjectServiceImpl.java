package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.ProjectModifyRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectRegisterRequest;
import com.nhn.sadari.minidooray.task.entity.Project;
import com.nhn.sadari.minidooray.task.entity.ProjectMember;
import com.nhn.sadari.minidooray.task.entity.ProjectStatus;
import com.nhn.sadari.minidooray.task.enumclass.ProjectMemberRoleType;
import com.nhn.sadari.minidooray.task.enumclass.ProjectStatusType;
import com.nhn.sadari.minidooray.task.exception.ProjectNotFoundException;
import com.nhn.sadari.minidooray.task.exception.ProjectStatusNotFoundException;
import com.nhn.sadari.minidooray.task.repository.ProjectMemberRepository;
import com.nhn.sadari.minidooray.task.repository.ProjectRepository;
import com.nhn.sadari.minidooray.task.repository.ProjectStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projectService")
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository projectStatusRepository;
    private final ProjectMemberRepository projectMemberRepository;

    private Project getProject(long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        return project;
    }

    //프로젝트 등록
    @Override
    @Transactional
    public Long createProject(ProjectRegisterRequest projectRegisterRequest) {

        Project project = new Project();
        project.setName(projectRegisterRequest.getName());
        project.setDescription(projectRegisterRequest.getDescription());

        ProjectStatus projectStatus = projectStatusRepository.findByStatus(ProjectStatusType.활성);
        project.setProjectStatus(projectStatus);

        projectRepository.save(project);

        ProjectMember projectMember = new ProjectMember();
        ProjectMember.Pk pk = new ProjectMember.Pk();
        pk.setProjectId(project.getId());
        pk.setMemberId(projectRegisterRequest.getMemberId());
        projectMember.setPk(pk);
        projectMember.setProject(project);
        projectMember.setMemberName(projectRegisterRequest.getMemberName());
        projectMember.setRole(ProjectMemberRoleType.관리자);

        projectMemberRepository.save(projectMember);

        return project.getId();
    }

    //프로젝트 수정
    @Override
    @Transactional
    public Long modifyProject(Long projectId, ProjectModifyRequest projectModifyRequest) {

        Project project = getProject(projectId);

        project.setName(projectModifyRequest.getName());
        project.setDescription(projectModifyRequest.getDescription());

        ProjectStatusType projectStatusType = projectModifyRequest.getStatus();
        ProjectStatus projectStatus = projectStatusRepository.findByStatus(projectStatusType);
        project.setProjectStatus(projectStatus);

        projectRepository.save(project);

        return project.getId();
    }

    //프로젝트 삭제
    @Override
    @Transactional
    public Long deleteProject(Long projectId) {
        Project project = getProject(projectId);
        projectRepository.delete(project);

        return project.getId();
    }
}