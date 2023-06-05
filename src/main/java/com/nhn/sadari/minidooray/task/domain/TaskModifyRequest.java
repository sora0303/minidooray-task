package com.nhn.sadari.minidooray.task.domain;

import com.sun.istack.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskModifyRequest {

    @NotNull
    String title;

    @NotNull
    String content;

    @NotNull
    LocalDateTime createdAt;

    LocalDate endDate;
    Long milestoneId;
    List<Long> memberIds;
    List<Long> tagIds;
}
