package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.domain.ProjectDto;
import com.nhn.sadari.minidooray.task.entity.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    ProjectDto getProjectById(Long projectId);
}
