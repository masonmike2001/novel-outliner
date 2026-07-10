package com.mikemason.novel_outliner.data.repositories;

import com.mikemason.novel_outliner.data.entities.BeatSegment;
import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeatSegmentRepository extends JpaRepository<BeatSegment, Long> {
    Optional<BeatSegment> findById(Long id);
}
