package com.app.jiralite.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(
    name="tasks",
    indexes = {
        @Index(name="idx_tasks_workspace", columnList="workspace_id"),
        @Index(name="idx_tasks_workspace_project", columnList="workspace_id,project_id"),
        @Index(name="idx_tasks_workspace_title", columnList="workspace_id,title")
    }
)
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="workspace_id")
    private Workspace workspace;

    @ManyToOne(optional=false)
    @JoinColumn(name="project_id")
    private Project project;

    @Column(nullable=false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TaskStatus status = TaskStatus.TODO;

    private String priority;

    @ManyToOne
    @JoinColumn(name="assignee_id")
    private User assignee;

    @Column(nullable=false)
    private Instant createdAt = Instant.now();

    public Long getId() { return id; }
    public Workspace getWorkspace() { return workspace; }
    public Project getProject() { return project; }
    public String getTitle() { return title; }
    public TaskStatus getStatus() { return status; }
    public String getPriority() { return priority; }
    public User getAssignee() { return assignee; }
    public Instant getCreatedAt() { return createdAt; }

    public void setWorkspace(Workspace workspace) { this.workspace = workspace; }
    public void setProject(Project project) { this.project = project; }
    public void setTitle(String title) { this.title = title; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setAssignee(User assignee) { this.assignee = assignee; }
}
