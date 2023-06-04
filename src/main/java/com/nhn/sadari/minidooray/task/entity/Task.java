package com.nhn.sadari.minidooray.task.entity;

import com.sun.istack.NotNull;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "member_id")
    @NotNull
    private Long memberId;

    @OneToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
