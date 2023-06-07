package com.nhn.sadari.minidooray.task.entity;

import com.sun.istack.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "writer_id")
    @NotNull
    private Long writerId;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "task", orphanRemoval = true)
    private List<Comment> comment;

    @OneToMany(mappedBy = "task", orphanRemoval = true)
    private List<TaskManager> taskManagers;

    @OneToMany(mappedBy = "task", orphanRemoval = true)
    private List<TaskTag> taskTags;

}
