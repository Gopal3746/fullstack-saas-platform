package com.app.jiralite.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="workspaces")
public class Workspace {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private Instant createdAt = Instant.now();

    public Long getId() { return id; }
    public String getName() { return name; }
    public Instant getCreatedAt() { return createdAt; }

    public void setName(String name) { this.name = name; }
}
