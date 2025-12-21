package com.app.jiralite.repo;

import com.app.jiralite.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByWorkspaceIdAndTaskIdOrderByCreatedAtAsc(Long workspaceId, Long taskId);
}
