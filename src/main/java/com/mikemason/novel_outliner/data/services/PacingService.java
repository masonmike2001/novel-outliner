package com.mikemason.novel_outliner.data.services;

import com.mikemason.novel_outliner.data.entities.BeatSegment;
import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import com.mikemason.novel_outliner.data.entities.Chapter;
import com.mikemason.novel_outliner.data.entities.Project;
import com.mikemason.novel_outliner.data.repositories.BeatTemplateRepository;

import com.mikemason.novel_outliner.data.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class PacingService {
    private final ProjectRepository projectRepository;
    private final BeatTemplateRepository beatTemplateRepository;

    public PacingService(ProjectRepository projectRepository, BeatTemplateRepository beatTemplateRepository) {
        this.projectRepository = projectRepository;
        this.beatTemplateRepository = beatTemplateRepository;
    }
        public List<Integer> calculateSegmentWordCount(Long projectId, String sessionId, Long BeatTemplateId) {
        // takes in project id and beattemplate, comes up with target word counts for each of segments by using target word count
            Project project = projectRepository.findByIdAndSessionId(projectId, sessionId)
                    .orElseThrow(() -> new RuntimeException("Project not found"));

            BeatTemplate template = beatTemplateRepository.findByIdWithBeatSegments(BeatTemplateId)
                    .orElseThrow(() -> new RuntimeException(("Template not found")));

            List<BeatSegment> segments = template.getBeatSegments();
            List<Integer> targetWordCounts = new ArrayList<>();
            for (BeatSegment segment : segments) {
                Double percent = segment.getEndPercentage() - segment.getStartPercentage();
                targetWordCounts.add((int) Math.round(project.getTargetTotalWordCount() * percent));
            }
//            target word counts is a list of each segment's word goal
            return targetWordCounts;
        }
//        public List<Chapter> generateChapterList(List<Integer> targetWordCounts, Project project) {
////         using the target wordcounts for each segment (prev function
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