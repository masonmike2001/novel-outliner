package com.mikemason.novel_outliner;

import com.mikemason.novel_outliner.data.entities.BeatSegment;
import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import com.mikemason.novel_outliner.data.entities.User;
import com.mikemason.novel_outliner.data.repositories.BeatSegmentRepository;
import com.mikemason.novel_outliner.data.repositories.BeatTemplateRepository;
import com.mikemason.novel_outliner.data.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private final BeatTemplateRepository beatTemplateRepository;
    private final BeatSegmentRepository beatSegmentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(
            BeatTemplateRepository beatTemplateRepository,
            BeatSegmentRepository beatSegmentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder
    ) {
        this.beatTemplateRepository = beatTemplateRepository;
        this.beatSegmentRepository = beatSegmentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void run(String... args) {

        System.out.println("DataLoader started");
//        createAdmin();

        if (beatTemplateRepository.count() > 0) {
            System.out.println("Templates already exist. Skipping seed.");
            return;
        }


        createHeroJourney();
        createThreeAct();
        createFourAct();


        System.out.println("Templates seeded");
    }


    private void createHeroJourney() {

        BeatTemplate template = new BeatTemplate();

        template.setTitle("The Hero's Journey");
        template.setSummary(
                "The classic 12-stage narrative arc popularized by Joseph Campbell and Christopher Vogler."
        );


        template = beatTemplateRepository.save(template);


        saveSegment(
                "The Ordinary World",
                0.0,
                0.12,
                1,
                template
        );

        saveSegment(
                "The Call to Adventure",
                0.12,
                0.12,
                2,
                template
        );

        saveSegment(
                "The Refusal of the Call",
                0.12,
                0.25,
                3,
                template
        );

        saveSegment(
                "Meeting with the Mentor",
                0.12,
                0.25,
                4,
                template
        );

        saveSegment(
                "Crossing the First Threshold",
                0.25,
                0.25,
                5,
                template
        );

        saveSegment(
                "Tests, Allies, Enemies",
                0.25,
                0.50,
                6,
                template
        );

        saveSegment(
                "Approach to the Inmost Cave",
                0.50,
                0.50,
                7,
                template
        );

        saveSegment(
                "The Ordeal",
                0.50,
                0.75,
                8,
                template
        );

        saveSegment(
                "The Reward",
                0.75,
                0.80,
                9,
                template
        );

        saveSegment(
                "The Road Back",
                0.80,
                0.90,
                10,
                template
        );

        saveSegment(
                "The Resurrection",
                0.90,
                0.99,
                11,
                template
        );

        saveSegment(
                "Return with the Elixir",
                0.99,
                1.00,
                12,
                template
        );
    }


    private void createThreeAct() {

        BeatTemplate template = new BeatTemplate();

        template.setTitle("Three Act Structure");
        template.setSummary("This is the Three Act Structure");


        template = beatTemplateRepository.save(template);


        saveSegment(
                "Setup",
                0.0,
                0.25,
                1,
                template
        );

        saveSegment(
                "Confrontation",
                0.25,
                0.75,
                2,
                template
        );

        saveSegment(
                "Resolution",
                0.75,
                1.0,
                3,
                template
        );
    }


    private void createFourAct() {

        BeatTemplate template = new BeatTemplate();

        template.setTitle("Four Act Structure");
        template.setSummary("This is the Four Act Structure");


        template = beatTemplateRepository.save(template);


        saveSegment(
                "Setup",
                0.0,
                0.25,
                1,
                template
        );

        saveSegment(
                "Complication",
                0.25,
                0.50,
                2,
                template
        );

        saveSegment(
                "Confrontation",
                0.50,
                0.75,
                3,
                template
        );

        saveSegment(
                "Resolution",
                0.75,
                1.0,
                4,
                template
        );
    }


    private void saveSegment(
            String title,
            double start,
            double end,
            int order,
            BeatTemplate template
    ) {

        BeatSegment segment = new BeatSegment();

        segment.setTitle(title);
        segment.setStartPercentage(start);
        segment.setEndPercentage(end);
        segment.setSequenceOrder(order);
        segment.setTemplate(template);


        beatSegmentRepository.save(segment);
    }

    private void createAdmin() {

        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("password"));
        user.setEmail("me@mikemason.dev");

        userRepository.save(user);
    }
}
