package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.entity.TaskTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.Pk> {

    List<TaskTag> findAllByPk_TaskId(Long taskId);

    void deleteAllByPk_TaskId(Long taskId);

}
