package com.nhn.sadari.minidooray.task.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task_manager")
public class TaskManager {

    @EmbeddedId
    private Pk pk;

    @MapsId("taskId")
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    @EqualsAndHashCode
    @Getter
    @Setter
    public static class Pk implements Serializable{

        @Column(name = "member_id")
        private Long memberId;

        @Column(name = "task_id")
        private Long taskId;

    }
}
