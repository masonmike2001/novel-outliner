package com.mikemason.novel_outliner;

import com.mikemason.novel_outliner.data.entities.BeatSegment;
import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import com.mikemason.novel_outliner.data.entities.Chapter;
import com.mikemason.novel_outliner.data.entities.Project;
import com.mikemason.novel_outliner.data.repositories.BeatSegmentRepository;
import com.mikemason.novel_outliner.data.repositories.BeatTemplateRepository;
import com.mikemason.novel_outliner.data.repositories.ChapterRepository;
import com.mikemason.novel_outliner.data.repositories.ProjectRepository;
import com.mikemason.novel_outliner.data.services.PacingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DataLoader implements CommandLineRunner {

    private final ProjectRepository projectRepository;
    private final BeatTemplateRepository beatTemplateRepository;
    private final BeatSegmentRepository beatSegmentRepository;
    private final ChapterRepository chapterRepository;

    @Autowired
    public DataLoader(ProjectRepository projectRepository, ChapterRepository chapterRepository, BeatTemplateRepository beatTemplateRepository, BeatSegmentRepository beatSegmentRepository) {
        this.projectRepository = projectRepository;
        this.chapterRepository = chapterRepository;
        this.beatTemplateRepository = beatTemplateRepository;
        this.beatSegmentRepository = beatSegmentRepository;


    }

    @Override
    @Transactional
    public void run(String... args) {
        System.out.println("DEBUG: DataLoader is being initialized by Spring!");
        // Clear existing data
        chapterRepository.deleteAll();
        projectRepository.deleteAll();
        beatSegmentRepository.deleteAll();
        beatTemplateRepository.deleteAll();

        // Example session ID
        String sessionId = "SESSION123";

        // Create Project
        Project project = new Project();
        project.setTitle("My First Project");
        project.setTargetTotalWordCount(15000);
        project.setSessionId(sessionId);

//        // Create Chapters
//        Chapter chapter1 = new Chapter();
//        chapter1.setWordCount(1000);
//        chapter1.setSequenceOrder(1);
//        chapter1.setSessionId(sessionId);
//        chapter1.setProject(project); // associate with project
//
//        Chapter chapter2 = new Chapter();
//        chapter2.setWordCount(1500);
//        chapter2.setSequenceOrder(2);
//        chapter2.setSessionId(sessionId);
//        chapter2.setProject(project);
//
//        // Add chapters to project
//        project.getChapters().add(chapter1);
//        project.getChapters().add(chapter2);
//
//        // Save project (cascade will save chapters automatically)
        projectRepository.save(project);

        System.out.println("Sample project created!");


        // https://www.storystructures.com/explore/threeact

        BeatTemplate threeAct = new BeatTemplate();
        threeAct.setTitle("Three Act Structure");
        threeAct.setSummary("This is the Three Act Structure");

        BeatSegment setup = new BeatSegment();
        setup.setTitle("Act 1: Setup");
        setup.setStartPercentage(0.0);
        setup.setEndPercentage(0.25);
        setup.setSequenceOrder(1);
        setup.setTemplate(threeAct);

        BeatSegment confrontation = new BeatSegment();
        confrontation.setTitle("Act 2: Confrontation");
        confrontation.setStartPercentage(0.25);
        confrontation.setEndPercentage(0.75);
        confrontation.setSequenceOrder(2);
        confrontation.setTemplate(threeAct);

        BeatSegment resolution = new BeatSegment();
        resolution.setTitle("Act 3: Resolution");
        resolution.setStartPercentage(0.75);
        resolution.setEndPercentage(1.0);
        resolution.setSequenceOrder(3);
        resolution.setTemplate(threeAct);


        // Add beat segments to project
        threeAct.getBeatSegments().add(setup);
        threeAct.getBeatSegments().add(confrontation);
        threeAct.getBeatSegments().add(resolution);

        // Save project (cascade will save templates automatically)
        beatTemplateRepository.save(threeAct);


//      https://www.storystructures.com/explore/fouract
        BeatTemplate fourAct = new BeatTemplate();
        fourAct.setTitle("Four Act Structure");
        fourAct.setSummary("This is the Four Act Structure");

        BeatSegment setupFour = new BeatSegment();
        setupFour.setTitle("Act 1: Setup");
        setupFour.setStartPercentage(0.0);
        setupFour.setEndPercentage(0.25);
        setupFour.setSequenceOrder(1);
        setupFour.setTemplate(fourAct);

        BeatSegment complicationFour = new BeatSegment();
        complicationFour.setTitle("Act 3: Complication");
        complicationFour.setStartPercentage(0.25);
        complicationFour.setEndPercentage(0.50);
        complicationFour.setSequenceOrder(2);
        complicationFour.setTemplate(fourAct);

        BeatSegment confrontationFour= new BeatSegment();
        confrontationFour.setTitle("Act 3: Confrontation");
        confrontationFour.setStartPercentage(0.50);
        confrontationFour.setEndPercentage(0.75);
        confrontationFour.setSequenceOrder(3);
        confrontationFour.setTemplate(fourAct);


        BeatSegment resolutionFour = new BeatSegment();
        resolutionFour.setTitle("Act 4: Resolution");
        resolutionFour.setStartPercentage(0.75);
        resolutionFour.setEndPercentage(1.0);
        resolutionFour.setSequenceOrder(4);
        resolutionFour.setTemplate(fourAct);


        // Add beat segments to project
        fourAct.getBeatSegments().add(setupFour);
        fourAct.getBeatSegments().add(complicationFour);
        fourAct.getBeatSegments().add(confrontationFour);
        fourAct.getBeatSegments().add(resolutionFour);

        beatTemplateRepository.save(fourAct);



        System.out.println("Beat template and segments created!");

        PacingService pacingService = new PacingService(projectRepository, beatTemplateRepository);

        List<Integer> segmentWordCounts = pacingService.calculateSegmentWordCount(project.getId(), sessionId, threeAct.getId());
        System.out.println(segmentWordCounts);

        pacingService.generateChapters(threeAct.getBeatSegments(), project, sessionId, 4000);

    }
}