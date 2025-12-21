package com.app.jiralite.repo;

import com.app.jiralite.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByWorkspaceId(Long workspaceId, Pageable pageable);
    Optional<Project> findByIdAndWorkspaceId(Long id, Long workspaceId);
}
