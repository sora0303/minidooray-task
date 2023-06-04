package com.nhn.sadari.minidooray.task.entity;


import com.sun.istack.NotNull;
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
public class Tag {

    @Id
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
