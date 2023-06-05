package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.entity.Project;
import com.nhn.sadari.minidooray.task.entity.Tag;
import com.nhn.sadari.minidooray.task.exception.ProjectNotFoundException;
import com.nhn.sadari.minidooray.task.exception.TagNotFoundException;
import com.nhn.sadari.minidooray.task.repository.ProjectRepository;
import com.nhn.sadari.minidooray.task.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tagService")
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    private Project getProject(long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        return project;
    }

    private Tag getTag(Long tagId){
        return tagRepository.findById(tagId).orElseThrow(() -> new TagNotFoundException(tagId));
    }

    //태그 등록 /api/projects/{projectId}/tags
    @Override
    @Transactional
    public Long createTag(Long projectId, TagRegisterRequest tagRegisterRequest){

        Project project = getProject(projectId);

        Tag tag = new Tag();

        tag.setName(tagRegisterRequest.getName());
        tag.setProject(project);

        tagRepository.save(tag);

        return tag.getId();
    }


    //태그 수정 /api/projects/{projectId}/tags/{tagId}
    @Override
    @Transactional
    public Long modifyTag(Long projectId, Long tagId, TagRegisterRequest tagRegisterRequest){

        Project project = getProject(projectId);
        Tag tag = getTag(tagId);

        tag.setName(tagRegisterRequest.getName());
        tagRepository.save(tag);

        return tag.getId();
    }


    //태그 삭제 DELETE /api/projects/{projectId}/tags/{tagId}
    @Override
    @Transactional
    public Long deleteTag(Long projectId, Long tagId){

        Project project = getProject(projectId);
        Tag tag = getTag(tagId);

        tagRepository.delete(tag);
        return tag.getId();
    }


}
