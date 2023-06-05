package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.MilestoneRegisterRequest;

public interface MilestoneService {

    Long createMilestone(Long projectId, MilestoneRegisterRequest milestoneRegisterRequest);

    Long modifyMilestone(Long projectId, Long milestoneId, MilestoneRegisterRequest milestoneRegisterRequest);

    Long deleteMilestone(Long projectId, Long milestoneId);
}
