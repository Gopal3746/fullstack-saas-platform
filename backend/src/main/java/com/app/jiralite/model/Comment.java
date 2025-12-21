package com.app.jiralite.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(
    name = "comments",
    indexes = {
      @Index(name = "idx_comments_workspace", columnList = "workspace_id"),
      @Index(name = "idx_comments_workspace_task", columnList = "workspace_id,task_id")
    })
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "workspace_id")
  private Workspace workspace;

  @ManyToOne(optional = false)
  @JoinColumn(name = "task_id")
  private Task task;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(nullable = false, length = 2000)
  private String body;

  @Column(nullable = false)
  private Instant createdAt = Instant.now();

  public Long getId() {
    return id;
  }

  public Workspace getWorkspace() {
    return workspace;
  }

  public Task getTask() {
    return task;
  }

  public User getUser() {
    return user;
  }

  public String getBody() {
    return body;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setWorkspace(Workspace workspace) {
    this.workspace = workspace;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
