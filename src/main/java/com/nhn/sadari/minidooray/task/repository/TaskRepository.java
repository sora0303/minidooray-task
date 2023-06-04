package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
