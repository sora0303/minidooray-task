package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.domain.ProjectMemberDto;
import com.nhn.sadari.minidooray.task.entity.ProjectMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk> {

    List<ProjectMemberDto> getProjectMembersByPk_ProjectId(Long projectId);

}
