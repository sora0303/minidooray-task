package com.nhn.sadari.minidooray.task.domain;

import com.nhn.sadari.minidooray.task.entity.ProjectMember;
import com.nhn.sadari.minidooray.task.entity.TaskManager;
import com.nhn.sadari.minidooray.task.entity.TaskTag;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private String content;
    private LocalDate endDate;
    private Long writerId;
    private LocalDateTime createdAt;

    private List<Long> memberIds = new ArrayList<>();;

    private Long milestoneId;

    private List<Long> tagIds = new ArrayList<>();;

    private String writerName;

    private List<String> memberNames = new ArrayList<>();;

    private List<String> tagNames = new ArrayList<>();


    @Builder
    public TaskDto(Long id, String title, String content, LocalDate endDate, Long writerId, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.endDate = endDate;
        this.writerId = writerId;
        this.createdAt = createdAt;
        this.memberIds  = new ArrayList<>();;
        this.tagIds = new ArrayList<>();;
        this.memberNames =  new ArrayList<>();;
        this.tagNames = new ArrayList<>();;
    }

    public void modifyMilestone(MilestoneDto milestoneDto){
        this.milestoneId = milestoneDto.getId();
    }

    public void modifyTag(TagDto tagDto){
        this.tagIds.add(tagDto.getId());
        this.tagNames.add(tagDto.getName());
    }

    public void modifyMember(ProjectMemberDto projectMemberDto){
        this.memberIds.add(projectMemberDto.getPk_memberId());
        this.memberNames.add(projectMemberDto.getMemberName());
    }

    public void modifyWriterName(ProjectMemberDto projectMemberDto){
        this.writerName = projectMemberDto.getMemberName();
    }




}