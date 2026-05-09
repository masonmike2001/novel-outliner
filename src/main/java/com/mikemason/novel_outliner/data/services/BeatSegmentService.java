package com.mikemason.novel_outliner.data.services;

import com.mikemason.novel_outliner.data.entities.BeatSegment;
import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import com.mikemason.novel_outliner.data.entities.Chapter;
import com.mikemason.novel_outliner.data.repositories.BeatSegmentRepository;
import com.mikemason.novel_outliner.data.repositories.ChapterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mikemason.novel_outliner.data.services.ChapterService.chapterRepository;

@Service
public class BeatSegmentService {

    private final BeatSegmentRepository beatSegmentRepository;

    public BeatSegmentService(BeatSegmentRepository beatSegmentRepository) {
        this.beatSegmentRepository = beatSegmentRepository;
    }

    public BeatSegment save(BeatSegment beatSegment) {
        // optional: if you want to find first
        // Optional<BeatSegment> existing = beatSegmentRepository.findById(beatSegment.getId());

        return beatSegmentRepository.save(beatSegment);
    }

    public List<BeatSegment> getBeatSegments() {
        return beatSegmentRepository.findAll();
    }

    public void deleteBeatSegment(Long id) {

        BeatSegment beatSegment = beatSegmentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Segment not found"));
        beatSegmentRepository.delete(beatSegment);
    }
}