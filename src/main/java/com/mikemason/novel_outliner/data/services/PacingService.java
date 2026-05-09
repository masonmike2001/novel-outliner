package com.mikemason.novel_outliner.data.services;

import com.mikemason.novel_outliner.data.entities.Chapter;
import com.mikemason.novel_outliner.data.entities.Project;
import com.mikemason.novel_outliner.data.repositories.ChapterRepository;
import com.mikemason.novel_outliner.data.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacingService {
    private final ProjectRepository projectRepository;

    public PacingService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

//        public List<Integer> calculateTargetWordCount(Long projectId, String sessionId, Long BeatTemplateId) {
//        // takes in project id and beattemplate, comes up with target word counts for chapters
//
//            Optional<Project> project = projectRepository.findByIdAndSessionId(projectId, sessionId);
//
//
//        }


//    public List<Double> calculateHeatmap(Long projectId) {
//        List<Chapter> chapters = projectRepository.findBySessionIdOrderById(projectId, );
//
///*        Find all chapters in project id/session id
//          Target word count is goal
//          (Make a feature so that if a chapter is unfinished you can null it with check box out so that the percentages aren't counted for that stage for target )
//
//          Iterate through all chapters
//            Compare the chapter wordcount with the start/end percentage on the corresponding stage
//            If wordcount
//
//
//
//
//
//  */
//
//
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
}