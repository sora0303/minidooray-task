package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.MilestoneDto;
import com.nhn.sadari.minidooray.task.domain.MilestoneRegisterRequest;
import java.util.List;

public interface MilestoneService {

    Long createMilestone(Long projectId, MilestoneRegisterRequest milestoneRegisterRequest);

    Long modifyMilestone(Long projectId, Long milestoneId, MilestoneRegisterRequest milestoneRegisterRequest);

    Long deleteMilestone(Long projectId, Long milestoneId);

    List<MilestoneDto> getMilestonesByProjectId(Long projectId);
}
