package com.mikemason.novel_outliner.data.services;

import com.mikemason.novel_outliner.data.entities.Chapter;
import com.mikemason.novel_outliner.data.repositories.ChapterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    public static ChapterRepository chapterRepository = null;

    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    public static Chapter save(Chapter chapter, String sessionId) {
        chapterRepository.findBySessionIdOrderById(sessionId);
        chapter.setSessionId(sessionId);
        return chapterRepository.save(chapter);
    }


// Data isolation:        chapterRepository.findBySessionId(sessionId);


    public static List<Chapter> getChapters(String sessionId) {
        return chapterRepository.findBySessionIdOrderById(sessionId);
    }

    public void deleteChapter(Long id, String sessionId) {

        Chapter chapter = chapterRepository
                .findByIdAndSessionId(id, sessionId)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));

        chapterRepository.delete(chapter);
    }
}