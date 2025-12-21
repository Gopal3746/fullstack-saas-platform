package com.app.jiralite.repo;

import com.app.jiralite.model.WorkspaceMember;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMember, Long> {
  Optional<WorkspaceMember> findByWorkspaceIdAndUserId(Long workspaceId, Long userId);

  List<WorkspaceMember> findByUserId(Long userId);

  List<WorkspaceMember> findByWorkspaceId(Long workspaceId);
}
