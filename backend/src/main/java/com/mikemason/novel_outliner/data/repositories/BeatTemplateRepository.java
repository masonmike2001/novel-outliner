package com.mikemason.novel_outliner.data.repositories;

import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeatTemplateRepository extends JpaRepository<BeatTemplate, Long> {
    Optional<BeatTemplate> findById(Long id);
    @Query("SELECT bt FROM BeatTemplate bt LEFT JOIN FETCH bt.beatSegments WHERE bt.id = :id")
    Optional<BeatTemplate> findByIdWithBeatSegments(@Param("id") Long id);
}
