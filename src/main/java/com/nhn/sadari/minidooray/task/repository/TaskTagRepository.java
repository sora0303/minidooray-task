package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.Pk> {
}
