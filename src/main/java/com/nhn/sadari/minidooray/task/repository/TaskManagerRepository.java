package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.entity.TaskManager;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskManagerRepository extends JpaRepository<TaskManager, TaskManager.Pk> {

    List<TaskManager> findAllByPk_TaskId(Long taskId);

    void deleteAllByPk_TaskId(Long taskId);

}
