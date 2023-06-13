package com.nhn.sadari.minidooray.task.domain;

import com.nhn.sadari.minidooray.task.entity.TaskManager;
import com.nhn.sadari.minidooray.task.entity.TaskTag;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskDto {

    String getTitle();

    String getContents();

    LocalDate getStartDate();

    LocalDate getEndDate();

    Long getWriterId();

    LocalDateTime getCreatedDate();

    List<Long> getTaskManagers_Pk_MemberId();

    List<Long> getTaskTags_Pk_TagId();

    Long getMilestoneId();

    List<Long> getComments_Id();


}
