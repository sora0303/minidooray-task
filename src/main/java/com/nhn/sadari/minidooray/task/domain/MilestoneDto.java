package com.nhn.sadari.minidooray.task.domain;

import java.time.LocalDate;

public interface MilestoneDto {

    Long getId();
    String getName();
    LocalDate getStartDate();
    LocalDate getEndDate();

}
