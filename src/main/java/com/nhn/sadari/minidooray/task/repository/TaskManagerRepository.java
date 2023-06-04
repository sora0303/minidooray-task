package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.entity.TaskManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskManagerRepository extends JpaRepository<TaskManager, TaskManager.Pk> {
}
