package com.mikemason.novel_outliner.data.repositories;

import com.mikemason.novel_outliner.data.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    // Custom query to get chapters for the heatmap in order
    List<Chapter> findBySessionIdOrderById(String sessionId);
    Optional<Chapter> findByIdAndSessionId(Long id, String sessionId);

    void deleteByCreatedAtBefore(LocalDateTime cutoff);
}
