package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
}
