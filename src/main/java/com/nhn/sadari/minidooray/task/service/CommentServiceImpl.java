package com.nhn.sadari.minidooray.task.service;

import com.nhn.sadari.minidooray.task.domain.CommentModifyRequest;
import com.nhn.sadari.minidooray.task.domain.CommentRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.TagRegisterRequest;
import com.nhn.sadari.minidooray.task.entity.Comment;
import com.nhn.sadari.minidooray.task.entity.Project;
import com.nhn.sadari.minidooray.task.entity.Tag;
import com.nhn.sadari.minidooray.task.entity.Task;
import com.nhn.sadari.minidooray.task.exception.CommentNotFoundException;
import com.nhn.sadari.minidooray.task.exception.TaskNotFoundException;
import com.nhn.sadari.minidooray.task.repository.CommentRepository;
import com.nhn.sadari.minidooray.task.repository.TaskRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("commentService")
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    private Comment getComment(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(commentId));
    }

    private Task getTask(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    //댓글 등록 /api/tasks/{tasksId}/comments
//    @Override
//    @Transactional
//    public Long createComment(Long taskId, CommentRegisterRequest commentRegisterRequest){
//
//        Task task = getTask(taskId);
//        Comment comment = new Comment();
//        comment.setWriterId(commentRegisterRequest.getWriterId());
//        comment.setCreatedAt(LocalDateTime.now());
//        comment.setTask(task);
//
//        commentRepository.save(comment);
//        return comment.getId();
//    }
//
//    //댓글 수정 /api/tasks/{tasksId}/comments/{commentId}
//    @Override
//    @Transactional
//    public Long modifyComment(Long taskId, Long commentId, CommentModifyRequest commentModifyRequest){
//
//        Task task = getTask(taskId);
//        Comment comment = getComment(commentId);
//        comment.setCreatedAt(LocalDateTime.now());
//        comment.setTask(task);
//
//        commentRepository.save(comment);
//        return comment.getId();
//    }

    //댓글 삭제 /api/tasks/{tasksId}/comments/{commentId}

}
