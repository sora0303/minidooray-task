package com.nhn.sadari.minidooray.task.domain;

import com.nhn.sadari.minidooray.task.enumclass.ProjectStatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface ProjectDto {

    Long getId();

    String getName();

    String getDescription();

    ProjectStatusType getProjectStatus_status();

}
