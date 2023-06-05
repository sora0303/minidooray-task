package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.MilestoneRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.entity.Milestone;
import com.nhn.sadari.minidooray.task.entity.Project;
import com.nhn.sadari.minidooray.task.entity.Tag;
import com.nhn.sadari.minidooray.task.exception.MilestoneNotFoundException;
import com.nhn.sadari.minidooray.task.exception.ProjectNotFoundException;
import com.nhn.sadari.minidooray.task.exception.TagNotFoundException;
import com.nhn.sadari.minidooray.task.repository.MilestoneRepository;
import com.nhn.sadari.minidooray.task.repository.ProjectRepository;
import com.nhn.sadari.minidooray.task.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("milestoneService")
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService{

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    private Project getProject(long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        return project;
    }

    private Milestone getMilestone(Long milestoneId){
        return milestoneRepository.findById(milestoneId).orElseThrow(() -> new MilestoneNotFoundException(milestoneId));
    }

    //마일스톤 등록 /api/projects/{projectId}/milestones
    @Override
    @Transactional
    public Long createMilestone(Long projectId, MilestoneRegisterRequest milestoneRegisterRequest){

        Project project = getProject(projectId);

        Milestone milestone = new Milestone();

        milestone.setName(milestoneRegisterRequest.getName());
        milestone.setStartDate(milestoneRegisterRequest.getStartDate());
        milestone.setEndDate(milestoneRegisterRequest.getEndDate());
        milestone.setProject(project);

        milestoneRepository.save(milestone);

        return milestone.getId();
    }


    //마일스톤 수정 /api/projects/{projectId}/milestones/{milestoneId}
    @Override
    @Transactional
    public Long modifyMilestone(Long projectId, Long milestoneId, MilestoneRegisterRequest milestoneRegisterRequest){

        Project project = getProject(projectId);
        Milestone milestone = getMilestone(milestoneId);

        milestone.setName(milestoneRegisterRequest.getName());
        milestone.setStartDate(milestoneRegisterRequest.getStartDate());
        milestone.setEndDate(milestoneRegisterRequest.getEndDate());

        return milestone.getId();
    }


    //마일스톤 삭제 DELETE /api/projects/{projectId}/milestones/{milestoneId}
    @Override
    @Transactional
    public Long deleteMilestone(Long projectId, Long milestoneId){

        Project project = getProject(projectId);
        Milestone milestone = getMilestone(milestoneId);

        milestoneRepository.delete(milestone);
        return milestone.getId();
    }
}
