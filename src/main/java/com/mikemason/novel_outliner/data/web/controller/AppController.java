package com.mikemason.novel_outliner.data.web.controller;


import com.mikemason.novel_outliner.data.dto.ProjectModel;
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

@Controller
public class AppController {
private final PacingService pacingService;
private final ProjectService projectService;
private final BeatTemplateRepository beatTemplateRepository;


    public AppController(PacingService pacingService, BeatTemplateRepository beatTemplateRepository, ProjectService projectService) {
        this.pacingService = pacingService;
        this.beatTemplateRepository = beatTemplateRepository;

        this.projectService = projectService;
    }
    @GetMapping("/app")
    public String getProjectModel(Model model) {
        ProjectModel projectModel = new ProjectModel("", 0, "none");
        model.addAttribute("projectModel", projectModel);
        return "app";
    }

    @PostMapping("/app")
    public String registerProject(@ModelAttribute ProjectModel projectModel, HttpSession session) {
        String sessionId = session.getId();
        Project project = new Project();
        project.setTitle(projectModel.projectTitle());
        project.setTargetTotalWordCount(projectModel.targetWordCount());
        project.setSessionId(sessionId);
        // beat template logic will go here, as well as chapter length
        int chapterLength = 4000;
        BeatTemplate beatTemplate = beatTemplateRepository.findAll().get(0);
        System.out.println("Registered: " + projectModel.projectTitle() + beatTemplate.getTitle());
        pacingService.generateChapters(beatTemplate.getBeatSegments(), project, sessionId, chapterLength);
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
