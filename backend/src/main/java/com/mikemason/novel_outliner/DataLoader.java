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

// Initialize the Hero's Journey Template
        BeatTemplate herosJourney = new BeatTemplate();
        herosJourney.setTitle("The Hero's Journey");
        herosJourney.setSummary("The classic 12-stage narrative arc popularized by Joseph Campbell and Christopher Vogler.");

// 1. The Ordinary World
        BeatSegment ordinaryWorld = new BeatSegment();
        ordinaryWorld.setTitle("The Ordinary World");
        ordinaryWorld.setStartPercentage(0.0);
        ordinaryWorld.setEndPercentage(0.12);
        ordinaryWorld.setSequenceOrder(1);
        ordinaryWorld.setTemplate(herosJourney);

// 2. The Call to Adventure
        BeatSegment callToAdventure = new BeatSegment();
        callToAdventure.setTitle("The Call to Adventure");
        callToAdventure.setStartPercentage(0.12);
        callToAdventure.setEndPercentage(0.12);
        callToAdventure.setSequenceOrder(2);
        callToAdventure.setTemplate(herosJourney);

// 3. The Refusal of the Call
        BeatSegment refusalOfCall = new BeatSegment();
        refusalOfCall.setTitle("The Refusal of the Call");
        refusalOfCall.setStartPercentage(0.12);
        refusalOfCall.setEndPercentage(0.25);
        refusalOfCall.setSequenceOrder(3);
        refusalOfCall.setTemplate(herosJourney);

// 4. Meeting with the Mentor
        BeatSegment meetingMentor = new BeatSegment();
        meetingMentor.setTitle("Meeting with the Mentor");
        meetingMentor.setStartPercentage(0.12);
        meetingMentor.setEndPercentage(0.25);
        meetingMentor.setSequenceOrder(4);
        meetingMentor.setTemplate(herosJourney);

// 5. Crossing the First Threshold
        BeatSegment crossingThreshold = new BeatSegment();
        crossingThreshold.setTitle("Crossing the First Threshold");
        crossingThreshold.setStartPercentage(0.25);
        crossingThreshold.setEndPercentage(0.25);
        crossingThreshold.setSequenceOrder(5);
        crossingThreshold.setTemplate(herosJourney);

// 6. Tests, Allies, Enemies
        BeatSegment testsAlliesEnemies = new BeatSegment();
        testsAlliesEnemies.setTitle("Tests, Allies, Enemies");
        testsAlliesEnemies.setStartPercentage(0.25);
        testsAlliesEnemies.setEndPercentage(0.50);
        testsAlliesEnemies.setSequenceOrder(6);
        testsAlliesEnemies.setTemplate(herosJourney);

// 7. Approach to the Inmost Cave
        BeatSegment approachCave = new BeatSegment();
        approachCave.setTitle("Approach to the Inmost Cave");
        approachCave.setStartPercentage(0.50);
        approachCave.setEndPercentage(0.50);
        approachCave.setSequenceOrder(7);
        approachCave.setTemplate(herosJourney);

// 8. The Ordeal
        BeatSegment theOrdeal = new BeatSegment();
        theOrdeal.setTitle("The Ordeal");
        theOrdeal.setStartPercentage(0.50);
        theOrdeal.setEndPercentage(0.75);
        theOrdeal.setSequenceOrder(8);
        theOrdeal.setTemplate(herosJourney);

// 9. The Reward
        BeatSegment theReward = new BeatSegment();
        theReward.setTitle("The Reward");
        theReward.setStartPercentage(0.75);
        theReward.setEndPercentage(0.80);
        theReward.setSequenceOrder(9);
        theReward.setTemplate(herosJourney);

// 10. The Road Back
        BeatSegment theRoadBack = new BeatSegment();
        theRoadBack.setTitle("The Road Back");
        theRoadBack.setStartPercentage(0.80);
        theRoadBack.setEndPercentage(0.90);
        theRoadBack.setSequenceOrder(10);
        theRoadBack.setTemplate(herosJourney);

// 11. The Resurrection
        BeatSegment theResurrection = new BeatSegment();
        theResurrection.setTitle("The Resurrection");
        theResurrection.setStartPercentage(0.90);
        theResurrection.setEndPercentage(0.99);
        theResurrection.setSequenceOrder(11);
        theResurrection.setTemplate(herosJourney);

// 12. Return with the Elixir
        BeatSegment returnWithElixir = new BeatSegment();
        returnWithElixir.setTitle("Return with the Elixir");
        returnWithElixir.setStartPercentage(0.99);
        returnWithElixir.setEndPercentage(1.00);
        returnWithElixir.setSequenceOrder(12);
        returnWithElixir.setTemplate(herosJourney);

// Add all beat segments to the template collection
        herosJourney.getBeatSegments().add(ordinaryWorld);
        herosJourney.getBeatSegments().add(callToAdventure);
        herosJourney.getBeatSegments().add(refusalOfCall);
        herosJourney.getBeatSegments().add(meetingMentor);
        herosJourney.getBeatSegments().add(crossingThreshold);
        herosJourney.getBeatSegments().add(testsAlliesEnemies);
        herosJourney.getBeatSegments().add(approachCave);
        herosJourney.getBeatSegments().add(theOrdeal);
        herosJourney.getBeatSegments().add(theReward);
        herosJourney.getBeatSegments().add(theRoadBack);
        herosJourney.getBeatSegments().add(theResurrection);
        herosJourney.getBeatSegments().add(returnWithElixir);

// Persist to the database via repository
        beatTemplateRepository.save(herosJourney);

        // https://www.storystructures.com/explore/threeact

        BeatTemplate threeAct = new BeatTemplate();
        threeAct.setTitle("Three Act Structure");
        threeAct.setSummary("This is the Three Act Structure");

        BeatSegment setup = new BeatSegment();
        setup.setTitle("Setup");
        setup.setStartPercentage(0.0);
        setup.setEndPercentage(0.25);
        setup.setSequenceOrder(1);
        setup.setTemplate(threeAct);

        BeatSegment confrontation = new BeatSegment();
        confrontation.setTitle("Confrontation");
        confrontation.setStartPercentage(0.25);
        confrontation.setEndPercentage(0.75);
        confrontation.setSequenceOrder(2);
        confrontation.setTemplate(threeAct);

        BeatSegment resolution = new BeatSegment();
        resolution.setTitle("Resolution");
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
        setupFour.setTitle("Setup");
        setupFour.setStartPercentage(0.0);
        setupFour.setEndPercentage(0.25);
        setupFour.setSequenceOrder(1);
        setupFour.setTemplate(fourAct);

        BeatSegment complicationFour = new BeatSegment();
        complicationFour.setTitle("Complication");
        complicationFour.setStartPercentage(0.25);
        complicationFour.setEndPercentage(0.50);
        complicationFour.setSequenceOrder(2);
        complicationFour.setTemplate(fourAct);

        BeatSegment confrontationFour= new BeatSegment();
        confrontationFour.setTitle("Confrontation");
        confrontationFour.setStartPercentage(0.50);
        confrontationFour.setEndPercentage(0.75);
        confrontationFour.setSequenceOrder(3);
        confrontationFour.setTemplate(fourAct);


        BeatSegment resolutionFour = new BeatSegment();
        resolutionFour.setTitle("Resolution");
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