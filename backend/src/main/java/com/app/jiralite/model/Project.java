package com.app.jiralite.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(
    name="projects",
    indexes = { @Index(name="idx_projects_workspace", columnList="workspace_id") }
)
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="workspace_id")
    private Workspace workspace;

    @Column(nullable=false)
    private String name;

    private String description;

    @Column(nullable=false)
    private Instant createdAt = Instant.now();

    public Long getId() { return id; }
    public Workspace getWorkspace() { return workspace; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Instant getCreatedAt() { return createdAt; }

    public void setWorkspace(Workspace workspace) { this.workspace = workspace; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
}
