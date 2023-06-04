package com.nhn.sadari.minidooray.task.repository;

import com.nhn.sadari.minidooray.task.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
