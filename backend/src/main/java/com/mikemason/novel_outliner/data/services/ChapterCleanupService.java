package com.mikemason.novel_outliner.data.services;

import com.mikemason.novel_outliner.data.repositories.ChapterRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChapterCleanupService {

    private final ChapterRepository chapterRepository;

    public ChapterCleanupService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @Scheduled(fixedRate = 3600000) // every hour
    @Transactional
    public void cleanupOldSessions() {

        LocalDateTime cutoff = LocalDateTime.now().minusHours(2);

        chapterRepository.deleteByCreatedAtBefore(cutoff);
    }
}