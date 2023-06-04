package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
