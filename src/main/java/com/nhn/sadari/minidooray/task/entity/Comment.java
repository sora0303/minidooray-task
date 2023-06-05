package com.nhn.sadari.minidooray.task.entity;


import com.nhn.sadari.minidooray.task.domain.CommentModifyRequest;
import com.sun.istack.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "writer_id")
    @NotNull
    private Long writerId;

    @Column(name = "create_at")
    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private String contents;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Builder
    public Comment(Long writerId, LocalDateTime createdAt, String contents, Task task) {
        this.writerId = writerId;
        this.createdAt = createdAt;
        this.contents = contents;
        this.task = task;
    }

    public void modifyComment(CommentModifyRequest commentModifyRequest) {
        this.contents = commentModifyRequest.getContents();
    }
}
