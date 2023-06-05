package com.nhn.sadari.minidooray.task.domain;


import com.sun.istack.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneRegisterRequest {

    @NotNull
    String name;

    LocalDate startDate;

    LocalDate endDate;

}
