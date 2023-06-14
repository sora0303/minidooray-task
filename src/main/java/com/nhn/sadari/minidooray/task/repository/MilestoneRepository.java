package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.domain.MilestoneDto;
import com.nhn.sadari.minidooray.task.entity.Milestone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<MilestoneDto> getMilestonesByProject_Id(Long projectId);

    MilestoneDto getMilestoneById(Long id);

}
