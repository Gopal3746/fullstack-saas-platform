package com.app.jiralite.repo;

import com.app.jiralite.model.Task;
import com.app.jiralite.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByWorkspaceIdAndProjectId(Long workspaceId, Long projectId, Pageable pageable);
    Page<Task> findByWorkspaceIdAndProjectIdAndStatus(Long workspaceId, Long projectId, TaskStatus status, Pageable pageable);
    Page<Task> findByWorkspaceIdAndProjectIdAndTitleContainingIgnoreCase(Long workspaceId, Long projectId, String q, Pageable pageable);
}
