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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        ProjectModel projectModel = new ProjectModel("", 0, "none", 0l);
        model.addAttribute("projectModel", projectModel);
        List<BeatTemplate> beatTemplates = beatTemplateRepository.findAll();
        model.addAttribute("beatTemplateList", beatTemplates);
        return "app";
    }

    @PostMapping("/app")
    public String registerProject(@ModelAttribute ProjectModel projectModel, HttpSession session, RedirectAttributes redirectAttributes) {
//        template id may be issue, may need to hardcode either by name or index
        BeatTemplate selected = beatTemplateRepository.findById(projectModel.selectedTemplateId())
                .orElseGet(() -> beatTemplateRepository.findAll().get(0)); // Static fallback to index 0


        String sessionId = session.getId();
        Project project = new Project();
        project.setTitle(projectModel.projectTitle());
        project.setTargetTotalWordCount(projectModel.targetWordCount());
        project.setSessionId(sessionId);
        project.setBeatTemplate(selected);
        // chapter length logic will go here
        int chapterLength = 4000;
//        BeatTemplate beatTemplate = beatTemplateRepository.findAll().get(0);
        System.out.println("Registered: " + projectModel.projectTitle());
        pacingService.generateChapters(selected.getBeatSegments(), project, sessionId, chapterLength);
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
