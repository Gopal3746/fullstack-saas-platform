package com.app.jiralite.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(
    name="workspace_members",
    uniqueConstraints = @UniqueConstraint(columnNames={"workspace_id","user_id"})
)
public class WorkspaceMember {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="workspace_id")
    private Workspace workspace;

    @ManyToOne(optional=false)
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role = Role.MEMBER;

    @Column(nullable=false)
    private Instant createdAt = Instant.now();

    public Long getId() { return id; }
    public Workspace getWorkspace() { return workspace; }
    public User getUser() { return user; }
    public Role getRole() { return role; }
    public Instant getCreatedAt() { return createdAt; }

    public void setWorkspace(Workspace workspace) { this.workspace = workspace; }
    public void setUser(User user) { this.user = user; }
    public void setRole(Role role) { this.role = role; }
}
