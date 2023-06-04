package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.entity.ProjectStatus;
import com.nhn.sadari.minidooray.task.enumclass.ProjectStatusType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Long> {

    ProjectStatus findByStatus(ProjectStatusType projectStatusType);

}
