package com.mikemason.novel_outliner.data.repositories;

import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeatTemplateRepository extends JpaRepository<BeatTemplate, Long> {
    Optional<BeatTemplate> findById(Long id);
}
