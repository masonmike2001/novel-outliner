//package com.mikemason.novel_outliner.data.services;
//
//import com.mikemason.novel_outliner.data.entities.Chapter;
//import com.mikemason.novel_outliner.data.repositories.ChapterRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class PacingService {
//    private final ChapterRepository chapterRepository;
//
//    public PacingService(ChapterRepository chapterRepository) {
//        this.chapterRepository = chapterRepository;
//    }
//    public List<Double> calculateHeatmap(Long projectId) {
//        List<Chapter> chapters = chapterRepository.findByProjectIdOrderBySequenceOrder(projectId);
//
//        // 1. Get Total Word Count
//        int totalWords = chapters.stream().mapToInt(Chapter::getWordCount).sum();
//
//        // 2. Map each chapter to a 'Heat' intensity (0.0 to 1.0)
//        // High heat = very long chapter (potential drag)
//        // Low heat = very short chapter (potential rush)
//        return chapters.stream()
//                .map(c -> (double) c.getWordCount() / totalWords)
//                .collect(Collectors.toList());
//    }
//}