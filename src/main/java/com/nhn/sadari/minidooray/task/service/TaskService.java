package com.nhn.sadari.minidooray.task.service;


import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.TaskModifyRequest;
import com.nhn.sadari.minidooray.task.domain.TaskRegisterRequest;

public interface TaskService {

    Long createTask(Long projectId, TaskRegisterRequest taskRegisterRequest);

    Long modifyTask(Long projectId, Long taskId, TaskModifyRequest taskModifyRequest);

    Long deleteTask(Long projectId, Long taskId);

}
