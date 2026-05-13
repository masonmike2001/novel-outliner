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
        public void generateChapters(List<Integer> targetWordCounts, Project project, String sessionId, int chapterLength) {
            int globalSequenceOrder = 1; // Start book at Chapter 1

            for (Integer segmentTotal : targetWordCounts) {
                // determine how many chapters this specific segment needs
                // rounds up so that 4500 words / 2000 length = 3 chapters
                // (Two full chapters and one partial)
                int chaptersInSegment = (int) Math.ceil((double) segmentTotal / chapterLength);

                for (int i = 0; i < chaptersInSegment; i++) {
                    Chapter chapter = new Chapter();

                    // 2. Handle the "Last Chapter" of a segment word count
                    if (i == chaptersInSegment - 1 && segmentTotal % chapterLength != 0) {
                        chapter.setWordCount(segmentTotal % chapterLength); // The remainder
                    } else {
                        chapter.setWordCount(chapterLength); // A full chapter
                    }

                    chapter.setSequenceOrder(globalSequenceOrder++);
                    chapter.setSessionId(sessionId);
                    chapter.setProject(project);

                    project.getChapters().add(chapter);
                }
            }

            projectRepository.save(project);
        }
}
