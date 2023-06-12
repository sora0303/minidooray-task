package com.nhn.sadari.minidooray.task.domain;

import java.time.LocalDate;

public interface MilestoneDto {

    String getName();
    LocalDate getStartDate();
    LocalDate getEndDate();

}
