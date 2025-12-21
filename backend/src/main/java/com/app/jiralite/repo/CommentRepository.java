package com.app.jiralite.repo;

import com.app.jiralite.model.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByWorkspaceIdAndTaskIdOrderByCreatedAtAsc(Long workspaceId, Long taskId);
}
