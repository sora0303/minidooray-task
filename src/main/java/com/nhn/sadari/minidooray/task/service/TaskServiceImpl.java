package com.nhn.sadari.minidooray.task.service;


import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import com.nhn.sadari.minidooray.task.domain.ProjectMemberDto;
import com.nhn.sadari.minidooray.task.domain.TagDto;
import com.nhn.sadari.minidooray.task.domain.TaskDto;
import com.nhn.sadari.minidooray.task.domain.TaskModifyRequest;
import com.nhn.sadari.minidooray.task.domain.TaskRegisterRequest;
import com.nhn.sadari.minidooray.task.entity.Milestone;
import com.nhn.sadari.minidooray.task.entity.Project;
import com.nhn.sadari.minidooray.task.entity.ProjectMember;
import com.nhn.sadari.minidooray.task.entity.QProjectMember;
import com.nhn.sadari.minidooray.task.entity.QTag;
import com.nhn.sadari.minidooray.task.entity.QTask;
import com.nhn.sadari.minidooray.task.entity.QTaskManager;
import com.nhn.sadari.minidooray.task.entity.QTaskTag;
import com.nhn.sadari.minidooray.task.entity.Tag;
import com.nhn.sadari.minidooray.task.entity.Task;
import com.nhn.sadari.minidooray.task.entity.TaskManager;
import com.nhn.sadari.minidooray.task.entity.TaskTag;
import com.nhn.sadari.minidooray.task.exception.MilestoneNotFoundException;
import com.nhn.sadari.minidooray.task.exception.ProjectNotFoundException;
import com.nhn.sadari.minidooray.task.exception.TagNotFoundException;
import com.nhn.sadari.minidooray.task.exception.TaskNotFoundException;
import com.nhn.sadari.minidooray.task.repository.MilestoneRepository;
import com.nhn.sadari.minidooray.task.repository.ProjectMemberRepository;
import com.nhn.sadari.minidooray.task.repository.ProjectRepository;
import com.nhn.sadari.minidooray.task.repository.TagRepository;
import com.nhn.sadari.minidooray.task.repository.TaskManagerRepository;
import com.nhn.sadari.minidooray.task.repository.TaskRepository;
import com.nhn.sadari.minidooray.task.repository.TaskTagRepository;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("taskService")
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    private final ProjectMemberRepository projectMemberRepository;

    private final MilestoneRepository milestoneRepository;

    private final TagRepository tagRepository;

    private final TaskTagRepository taskTagRepository;

    private final TaskManagerRepository taskManagerRepository;

    private final JPAQueryFactory queryFactory;


    private Task getTask(long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    private Project getProject(long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        return project;
    }

    private Milestone getMilestone(long milestoneId) {
        Milestone milestone =
            milestoneRepository.findById(milestoneId).orElseThrow(() -> new MilestoneNotFoundException(milestoneId));
        return milestone;
    }

    private Tag getTag(long tagId) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new TagNotFoundException(tagId));
        return tag;
    }

    //업무 등록
    @Override
    @Transactional
    public Long createTask(Long projectId, TaskRegisterRequest taskRegisterRequest) {

        Task task = new Task();
        task.setTitle(taskRegisterRequest.getTitle());
        task.setContent(taskRegisterRequest.getContent());
        task.setWriterId(taskRegisterRequest.getWriterId());
        task.setCreatedAt(LocalDateTime.now());
        task.setEndDate(taskRegisterRequest.getEndDate());

        Project project = getProject(projectId);
        task.setProject(project);

        if (Objects.nonNull(taskRegisterRequest.getMilestoneId())) {
            Milestone milestone = getMilestone(taskRegisterRequest.getMilestoneId());
            task.setMilestone(milestone);
        }

        taskRepository.save(task);

        //해당 Task에 따른 TaskManager 등록

        if (Objects.nonNull(taskRegisterRequest.getMemberIds())) {
            List<Long> memberIds = taskRegisterRequest.getMemberIds();
            for (Long memberId : memberIds) {
                TaskManager taskManager = new TaskManager();
                TaskManager.Pk pk = new TaskManager.Pk();
                pk.setMemberId(memberId);
                pk.setTaskId(task.getId());
                taskManager.setPk(pk);
                taskManager.setTask(task);
                taskManagerRepository.save(taskManager);
            }
        }


        //해당 Task에 따른 Tag 등록

        if (Objects.nonNull(taskRegisterRequest.getTagIds())) {
            List<Long> tagIds = taskRegisterRequest.getTagIds();
            for (Long tagId : tagIds) {
                Tag tag = getTag(tagId);
                TaskTag taskTag = new TaskTag();
                TaskTag.Pk pk = new TaskTag.Pk();
                pk.setTaskId(task.getId());
                pk.setTagId(tagId);
                taskTag.setPk(pk);
                taskTag.setTask(task);
                taskTag.setTag(tag);
                taskTagRepository.save(taskTag);
            }
        }

        return task.getId();
    }

    //업무 수정
    @Override
    @Transactional
    public Long modifyTask(Long projectId, Long taskId, TaskModifyRequest taskModifyRequest) {

        Project project = getProject(projectId);

        Task task = getTask(taskId);
        task.setTitle(taskModifyRequest.getTitle());
        task.setContent(taskModifyRequest.getContent());
        task.setCreatedAt(LocalDateTime.now());
        task.setEndDate(taskModifyRequest.getEndDate());

        if (Objects.nonNull(taskModifyRequest.getMilestoneId())) {
            Milestone milestone = getMilestone(taskModifyRequest.getMilestoneId());
            task.setMilestone(milestone);
        }

        taskRepository.save(task);

        //해당 Task에 따른 TaskManager 수정
        taskManagerRepository.deleteAllByPk_TaskId(taskId);

        if (Objects.nonNull(taskModifyRequest.getMemberIds())) {
            List<Long> memberIds = taskModifyRequest.getMemberIds();
            for (Long memberId : memberIds) {
                TaskManager taskManager = new TaskManager();
                TaskManager.Pk pk = new TaskManager.Pk();
                pk.setMemberId(memberId);
                pk.setTaskId(task.getId());
                taskManager.setPk(pk);
                taskManager.setTask(task);
                taskManagerRepository.save(taskManager);
            }
        }

        //해당 Task에 따른 Tag 수정
        taskTagRepository.deleteAllByPk_TaskId(taskId);

        if (Objects.nonNull(taskModifyRequest.getTagIds())) {
            List<Long> tagIds = taskModifyRequest.getTagIds();
            for (Long tagId : tagIds) {
                Tag tag = getTag(tagId);
                TaskTag taskTag = new TaskTag();
                TaskTag.Pk pk = new TaskTag.Pk();
                pk.setTaskId(task.getId());
                pk.setTagId(tagId);
                taskTag.setPk(pk);
                taskTag.setTask(task);
                taskTag.setTag(tag);
                taskTagRepository.save(taskTag);
            }
        }

        return task.getId();
    }

    //업무 삭제
    @Override
    @Transactional
    public Long deleteTask(Long projectId, Long taskId) {
        Project project = getProject(projectId);
        Task task = getTask(taskId);
        taskRepository.delete(task);
        return task.getId();
    }


    //업무 아이디로 업무 조회
    @Override
    public TaskDto getTaskByTaskId(Long taskId) {

        Task task = getTask(taskId);

        TaskDto taskDto = TaskDto.builder()
            .id(task.getId())
            .title(task.getTitle())
            .content(task.getContent())
            .endDate(task.getEndDate())
            .writerId(task.getWriterId())
            .createdAt(task.getCreatedAt())
            .build();

        if (Objects.nonNull(task.getMilestone())) {

            taskDto.modifyMilestone(milestoneRepository.getMilestoneById(task.getMilestone().getId()));
        }
        for (TaskManager taskManager : task.getTaskManagers()) {

            List<ProjectMemberDto> projectMembers =
                projectMemberRepository.findProjectMembersByPk_MemberId(taskManager.getPk().getMemberId());
            if (!projectMembers.isEmpty()) {
                ProjectMemberDto projectMemberDto = projectMembers.get(0);
                taskDto.modifyMember(projectMemberDto);
            }
        }

        for (TaskTag taskTag : task.getTaskTags()) {
            TagDto tagDto = tagRepository.getTagDtoById(taskTag.getPk().getTagId());
            taskDto.modifyTag(tagDto);
        }
        taskDto.modifyWriterName(projectMemberRepository.findProjectMembersByPk_MemberId((task.getWriterId())).get(0));

        return taskDto;
    }


    //프로젝트 아이디로 업무 조회
    public List<TaskDto> getTasksByProjectId(Long projectId) {
        Project project = getProject(projectId);
        List<Task> tasks = taskRepository.findAllByProject_Id(projectId);

        List<TaskDto> taskDtos = new ArrayList<>();

        for (Task task : tasks) {
            TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .content(task.getContent())
                .endDate(task.getEndDate())
                .writerId(task.getWriterId())
                .createdAt(task.getCreatedAt())
                .build();

            if (Objects.nonNull(task.getMilestone())) {

                taskDto.modifyMilestone(milestoneRepository.getMilestoneById(task.getMilestone().getId()));
            }
            for (TaskManager taskManager : task.getTaskManagers()) {

                List<ProjectMemberDto> projectMembers =
                    projectMemberRepository.findProjectMembersByPk_MemberId(taskManager.getPk().getMemberId());
                if (!projectMembers.isEmpty()) {
                    ProjectMemberDto projectMemberDto = projectMembers.get(0);
                    taskDto.modifyMember(projectMemberDto);
                }
            }


            for (TaskTag taskTag : task.getTaskTags()) {
                TagDto tagDto = tagRepository.getTagDtoById(taskTag.getPk().getTagId());
                taskDto.modifyTag(tagDto);
            }
            taskDto.modifyWriterName(projectMemberRepository.findProjectMembersByPk_MemberId((task.getWriterId())).get(0));

            taskDtos.add(taskDto);
        }

        return taskDtos;

    }


    //멤버 아이디로 멤버 네임 조회
//    public List<TaskDto> getTasksByProjectId(Long projectId) {
//        QTask task = QTask.task;
//        QTaskManager taskManager = QTaskManager.taskManager;
//        QTaskTag taskTag = QTaskTag.taskTag;
//        QTag tag = QTag.tag;
//        QProjectMember projectMember = QProjectMember.projectMember;
//
//        return queryFactory
//            .from(task)
//            .leftJoin(task.taskManagers, taskManager)
//            .leftJoin(task.taskTags, taskTag)
//            .leftJoin(task.project.projectMember, projectMember)
//            .leftJoin(taskTag.tag, tag)
//            .where(task.project.id.eq(projectId))
//            .transform(groupBy(task.id, tag.id).list(
//                Projections.fields(
//                    TaskDto.class,
//                    task.title.as("title"),
//                    task.content.as("content"),
//                    task.endDate.as("endDate"),
//                    task.writerId.as("writerId"),
//                    task.createdAt.as("createdAt"),
//                    task.milestone.id.as("milestoneId"),
//                    list(
//                        Projections.constructor(String.class,
//                            projectMember.memberName
//                        )
//                    ).as("memberName"),
//                    list(
//                        Projections.constructor(Long.class,
//                            projectMember.pk.memberId
//                        )
//                    ).as("memberId")
////
////                    list(
////                        Projections.constructor(Long.class,
////                            tag.id
////                        )
////                    ).as("tagId"),
////                    list(
////                        Projections.constructor(String.class,
////                            tag.name
////                        )
////                    ).as("tagName")
//                )
//            )
//            );
//
//    }

    //프로젝트 아이디로 업무리스트 조회


}



