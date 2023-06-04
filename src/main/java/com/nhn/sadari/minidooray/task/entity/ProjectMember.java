package com.nhn.sadari.minidooray.task.entity;


import com.nhn.sadari.minidooray.task.enumclass.ProjectMemberRoleType;
import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "project_member")
public class ProjectMember {

    @EmbeddedId
    private Pk pk;

    @MapsId("projectId")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "member_name")
    @NotNull
    private String memberName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProjectMemberRoleType role;

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {

        @Column(name = "member_id")
        private long memberId;

        @Column(name = "project_id")
        private long projectId;
    }

}
