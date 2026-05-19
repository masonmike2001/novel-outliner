package com.mikemason.novel_outliner.data.web.controller;

import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import com.mikemason.novel_outliner.data.entities.Project;
import com.mikemason.novel_outliner.data.repositories.BeatTemplateRepository;
import com.mikemason.novel_outliner.data.repositories.ProjectRepository;
import com.mikemason.novel_outliner.data.services.PacingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    public String registerProject(@ModelAttribute("project") Project project, HttpSession session, RedirectAttributes redirectAttributes) {
        if (project.getBeatTemplate() == null || project.getBeatTemplate().getId() == null) {
            project.setBeatTemplate(beatTemplateRepository.findAll().get(0));
        } else {
            BeatTemplate managedTemplate = beatTemplateRepository.findById(project.getBeatTemplate().getId()).orElseThrow();
            project.setBeatTemplate(managedTemplate);
        }

        project.setSessionId(session.getId());
        Project savedProject = projectRepository.save(project);

        pacingService.generateChapters(savedProject.getBeatTemplate().getBeatSegments(), project, project.getSessionId(), project.getChapterLength());
        redirectAttributes.addAttribute("projectId", savedProject.getId());
        return "redirect:/success";

    }
    @GetMapping("/success")
    public String showSuccess(@RequestParam("projectId") Long projectId, Model model) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found: " + projectId));
        model.addAttribute("project", project);
        return "success";
    }
}
