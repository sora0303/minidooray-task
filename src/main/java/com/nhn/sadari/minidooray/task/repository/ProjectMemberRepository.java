package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk> {
}
