package com.mikemason.novel_outliner.data.repositories;

import com.mikemason.novel_outliner.data.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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



    @Query("""
        SELECT p FROM Project p
        JOIN FETCH p.beatTemplate bt
        JOIN FETCH bt.beatSegments
        LEFT JOIN FETCH p.chapters c
        WHERE p.id = :id
    """)
    Optional<Project> findProjectWithDetails(@Param("id") Long id);
    void deleteByCreatedAtBefore(LocalDateTime cutoff);

    @Query("""
    SELECT DISTINCT p FROM Project p
    LEFT JOIN FETCH p.chapters
    WHERE p.id = :id
""")
    Optional<Project> findProjectWithChapters(@Param("id") Long id);


}
