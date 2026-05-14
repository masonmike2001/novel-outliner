package com.mikemason.novel_outliner.data.repositories;

import com.mikemason.novel_outliner.data.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Custom query to get chapters for the heatmap in order
    List<Project> findBySessionIdOrderById(String sessionId);
    Optional<Project> findByIdAndSessionId(Long id, String sessionId);
    Optional<Project> findBySessionId(String sessionId);

    void deleteByCreatedAtBefore(LocalDateTime cutoff);


}
