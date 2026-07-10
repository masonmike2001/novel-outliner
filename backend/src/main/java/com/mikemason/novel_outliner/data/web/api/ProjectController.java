package com.mikemason.novel_outliner.data.web.api;

import com.mikemason.novel_outliner.data.entities.Chapter;
import com.mikemason.novel_outliner.data.entities.Project;
import com.mikemason.novel_outliner.data.services.ChapterService;
import com.mikemason.novel_outliner.data.services.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects(
            @RequestParam(required = false) String sessionId,
            HttpSession session
    ) {
        String effectiveSessionId = (sessionId != null) ? sessionId : session.getId();
        return projectService.getProjects(effectiveSessionId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(
            @PathVariable Long id,
            HttpSession session
    ) {
        Project project = projectService.getProject(id, session.getId());
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public Project createProject(
            @RequestBody Project project,
            HttpSession session
    ) {
        return projectService.save(project, session.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long id,
            HttpSession session
    ) {
        projectService.deleteProject(id, session.getId());

        return ResponseEntity.noContent().build();
    }
}