package com.nhn.sadari.minidooray.task.domain;

import com.sun.istack.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRegisterRequest {

    @NotNull
    @NotBlank
    String title;

    @NotNull
    @NotBlank
    String content;

    @NotNull
    Long writerId;


    LocalDate endDate;
    Long milestoneId;
    List<Long> memberIds;
    List<Long> tagIds;
}
