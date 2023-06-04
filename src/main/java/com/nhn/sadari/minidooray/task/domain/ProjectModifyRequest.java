package com.nhn.sadari.minidooray.task.domain;

import com.nhn.sadari.minidooray.task.enumclass.ProjectStatusType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectModifyRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private ProjectStatusType status;

}
