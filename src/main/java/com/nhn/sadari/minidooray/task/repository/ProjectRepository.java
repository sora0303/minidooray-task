package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.domain.ProjectDto;
import com.nhn.sadari.minidooray.task.entity.Project;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    ProjectDto getProjectById(Long projectId);

    List<ProjectDto> getProjectsByProjectMember_Pk_MemberIdOrderById(Long memberId);
//    Page<ProjectDto> getProjectsByProjectMember_Pk_MemberIdOrderById(Pageable pageable, Long memberId);

}
