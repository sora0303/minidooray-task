package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.MilestoneRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.TaskRegisterRequest;
import com.nhn.sadari.minidooray.task.entity.Milestone;
import com.nhn.sadari.minidooray.task.entity.Project;
import com.nhn.sadari.minidooray.task.entity.Tag;
import com.nhn.sadari.minidooray.task.entity.Task;
import com.nhn.sadari.minidooray.task.entity.TaskManager;
import com.nhn.sadari.minidooray.task.entity.TaskTag;
import com.nhn.sadari.minidooray.task.exception.MilestoneNotFoundException;
import com.nhn.sadari.minidooray.task.exception.ProjectNotFoundException;
import com.nhn.sadari.minidooray.task.exception.TagNotFoundException;
import com.nhn.sadari.minidooray.task.exception.TaskNotFoundException;
import com.nhn.sadari.minidooray.task.repository.MilestoneRepository;
import com.nhn.sadari.minidooray.task.repository.ProjectRepository;
import com.nhn.sadari.minidooray.task.repository.TagRepository;
import com.nhn.sadari.minidooray.task.repository.TaskManagerRepository;
import com.nhn.sadari.minidooray.task.repository.TaskRepository;
import com.nhn.sadari.minidooray.task.repository.TaskTagRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("taskService")
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    private final MilestoneRepository milestoneRepository;

    private final TagRepository tagRepository;

    private final TaskTagRepository taskTagRepository;

    private final TaskManagerRepository taskManagerRepository;


    private Task getTask(long taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    private Project getProject(long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        return project;
    }

    private Milestone getMilestone(long milestoneId){
        Milestone milestone = milestoneRepository.findById(milestoneId).orElseThrow(() -> new MilestoneNotFoundException(milestoneId));
        return milestone;
    }

    private Tag getTag(long tagId){
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new TagNotFoundException(tagId));
        return tag;
    }

    public Long createTask(Long projectId, TaskRegisterRequest taskRegisterRequest){

        Task task = new Task();
        task.setTitle(taskRegisterRequest.getTitle());
        task.setContent(taskRegisterRequest.getContent());
        task.setWriterId(taskRegisterRequest.getWriterId());
        task.setCreatedAt(LocalDateTime.now());
        task.setEndDate(taskRegisterRequest.getEndDate());

        Project project = getProject(projectId);
        task.setProject(project);

        Milestone milestone = getMilestone(taskRegisterRequest.getMilestoneId());
        task.setMilestone(milestone);

        taskRepository.save(task);

        //해당 Task에 따른 TaskManager 등록
        List<Long> memberIds = taskRegisterRequest.getMemberIds();

        for(Long memberId : memberIds) {
            TaskManager taskManager = new TaskManager();
            TaskManager.Pk pk = new TaskManager.Pk();
            pk.setMemberId(memberId);
            pk.setTaskId(task.getId());
            taskManager.setPk(pk);
            taskManagerRepository.save(taskManager);
        }

        //해당 Task에 따른 Tag 등록
        List<Long> tagIds = taskRegisterRequest.getTagIds();

        for(Long tagId : tagIds) {
            Tag tag = getTag(tagId);
            TaskTag taskTag = new TaskTag();
            TaskTag.Pk pk = new TaskTag.Pk();
            pk.setTaskId(task.getId());
            pk.setTagId(tagId);
            taskTag.setPk(pk);
            taskTagRepository.save(taskTag);
        }

        return task.getId();
    }


}
