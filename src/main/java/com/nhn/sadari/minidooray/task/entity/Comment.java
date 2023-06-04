package com.nhn.sadari.minidooray.task.entity;


import com.sun.istack.NotNull;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Comment {

    @Id
    private Long id;

    @Column(name = "member_id")
    @NotNull
    private Long memberId;

    @Column(name = "create_at")
    @NotNull
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}
