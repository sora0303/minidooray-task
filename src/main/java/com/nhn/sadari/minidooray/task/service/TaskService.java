package com.nhn.sadari.minidooray.task.service;


import com.nhn.sadari.minidooray.task.domain.TagDto;
import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.TaskDto;
import com.nhn.sadari.minidooray.task.domain.TaskModifyRequest;
import com.nhn.sadari.minidooray.task.domain.TaskRegisterRequest;
import java.util.List;

public interface TaskService {

    Long createTask(Long projectId, TaskRegisterRequest taskRegisterRequest);

    Long modifyTask(Long projectId, Long taskId, TaskModifyRequest taskModifyRequest);

    Long deleteTask(Long projectId, Long taskId);

    List<TaskDto> getTasksByProjectId(Long projectId);

    TaskDto getTaskByTaskId(Long taskId);
}
