package com.mikemason.novel_outliner.data.web.controller;

import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import com.mikemason.novel_outliner.data.entities.Project;
import com.mikemason.novel_outliner.data.repositories.BeatTemplateRepository;
import com.mikemason.novel_outliner.data.repositories.ProjectRepository;
import com.mikemason.novel_outliner.data.services.PacingService;
import com.mikemason.novel_outliner.data.services.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AppController {
private final PacingService pacingService;
private final ProjectRepository projectRepository;
private final BeatTemplateRepository beatTemplateRepository;


    public AppController(PacingService pacingService, BeatTemplateRepository beatTemplateRepository, ProjectRepository projectRepository) {
        this.pacingService = pacingService;
        this.beatTemplateRepository = beatTemplateRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/app")
    public String getProjectModel(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        model.addAttribute("beatTemplateList", beatTemplateRepository.findAll());

        return "app";
    }

    @PostMapping("/app")
    public String registerProject(@ModelAttribute("projectModel") Project project, HttpSession session, RedirectAttributes redirectAttributes) {
        BeatTemplate beatTemplate = project.getBeatTemplate();

        // 2. Fetch the fully managed BeatTemplate entity from DB

        // 3. Link the full template and session details back to the project

        project.setBeatTemplate(beatTemplate);
        project.setSessionId(session.getId());
        String submittedTitle = project.getTitle();
        Integer submittedChapterLength = project.getChapterLength();
        Integer targetTotalWordCount = project.getTargetTotalWordCount();


        // 4. Save to database
        projectRepository.save(project);





        System.out.println("Registered: " + project.getTitle());
        pacingService.generateChapters(beatTemplate.getBeatSegments(), project, project.getSessionId(), project.getChapterLength());

        return "redirect:/success";

    }
    @GetMapping("/success")
    public String showSuccess(HttpSession session, Model model) {
        String sessionId = session.getId();

        Project project = ProjectService.getProjects(sessionId).get(0);
        model.addAttribute("project", project);
        return "success";
    }
}
