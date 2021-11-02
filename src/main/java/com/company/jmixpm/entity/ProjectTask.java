package com.company.jmixpm.entity;

import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "PROJECT_TASK")
@Entity
public class ProjectTask {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @NotNull
    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @NotNull
    @PositiveOrZero
    @Column(name = "ESTIMATED_EFFORTS", nullable = false)
    private Integer estimatedEfforts;

    @JoinColumn(name = "ASSIGNEE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User assignee;

    @JoinColumn(name = "PROJECT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @PropertyDatatype("color")
    @Column(name = "COLOR")
    private String color;

    @Column(name = "ATTACHEMENT")
    private FileRef attachement;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public FileRef getAttachement() {
        return attachement;
    }

    public void setAttachement(FileRef attachement) {
        this.attachement = attachement;
    }

    @DependsOnProperties({"name", "project"})
    @Transient
    @JmixProperty
    public String getCaption() {
        return String.format("[%s] %s", project != null ? project.getName() : "", name);
    }

    @DependsOnProperties({"startDate", "estimatedEfforts", "endDate"})
    @Transient
    @JmixProperty
    public LocalDateTime getEstimatedEndDate() {
        if (endDate != null) {
            return endDate;
        } else if (startDate != null && estimatedEfforts != null) {
            return startDate.plusHours(estimatedEfforts);
        } else {
            return null;
        }
    }



    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Integer getEstimatedEfforts() {
        return estimatedEfforts;
    }

    public void setEstimatedEfforts(Integer estimatedEfforts) {
        this.estimatedEfforts = estimatedEfforts;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}