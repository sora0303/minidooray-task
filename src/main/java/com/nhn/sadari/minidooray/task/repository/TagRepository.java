package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.domain.TagDto;
import com.nhn.sadari.minidooray.task.entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

        List<TagDto> findAllByProject_Id(Long projectId);

}
