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
@Transactional
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
    public void generateChapters(List<BeatSegment> segments, Project project, String sessionId, int chapterLength) {
        int globalSequenceOrder = 1;

        for (BeatSegment segment : segments) {
            // Calculate the target word count for THIS specific segment
            double percent = segment.getEndPercentage() - segment.getStartPercentage();
            int segmentTotal = (int) Math.round(project.getTargetTotalWordCount() * percent);

            if (segmentTotal <= 0) continue;

            // determine chapters for this segment
            int chaptersInSegment = (int) Math.ceil((double) segmentTotal / chapterLength);

            int baseLength = segmentTotal / chaptersInSegment;
            int remainder = segmentTotal % chaptersInSegment;

            for (int i = 0; i < chaptersInSegment; i++) {
                Chapter chapter = new Chapter();


                int assignedWordCount = baseLength;
                if (remainder > 0) {
                    assignedWordCount += 1;
                    remainder--;
                }

                chapter.setWordCount(assignedWordCount);
                chapter.setSequenceOrder(globalSequenceOrder++);
                chapter.setSessionId(sessionId);
                chapter.setProject(project);


                System.out.println(segment.getId());
                chapter.setBeatSegment(segment);

                project.getChapters().add(chapter);
            }
        }

        // Save everything in one database transaction
        projectRepository.save(project);
        System.out.println("Saved chapters: " + project.getChapters().size());
    }
}
