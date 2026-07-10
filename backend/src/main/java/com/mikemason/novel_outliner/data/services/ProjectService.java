package com.mikemason.novel_outliner.data.services;



import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import com.mikemason.novel_outliner.data.entities.Project;
import com.mikemason.novel_outliner.data.repositories.BeatTemplateRepository;
import com.mikemason.novel_outliner.data.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private static ProjectRepository projectRepository = null;
    private static BeatTemplateRepository beatTemplateRepository = null;
    private static PacingService pacingService = null;

    public ProjectService(ProjectRepository projectRepository, BeatTemplateRepository beatTemplateRepository, PacingService pacingService ) {
        this.projectRepository = projectRepository;
        this.beatTemplateRepository = beatTemplateRepository;
        this.pacingService = pacingService;
    }

    public Project save(Project project, String sessionId) {

        Long templateId = project.getBeatTemplate().getId();

        BeatTemplate template = beatTemplateRepository
                .findByIdWithBeatSegments(templateId)
                .orElseThrow(() -> new RuntimeException("Beat template not found"));

        project.setBeatTemplate(template);
        project.setSessionId(sessionId);

        Project savedProject = projectRepository.save(project);

        pacingService.generateChapters(
                template.getBeatSegments(),
                savedProject,
                sessionId,
                savedProject.getChapterLength()
        );

        return savedProject;
    }


// Data isolation:        projectRepository.findBySessionId(sessionId);


    public static List<Project> getProjects(String sessionId) {
        return projectRepository.findBySessionIdOrderById(sessionId);
    }

    public void deleteProject(Long id, String sessionId) {

        Project project = projectRepository
                .findByIdAndSessionId(id, sessionId)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));

        projectRepository.delete(project);
    }

    public Project getProject(Long id, String sessionId) {

        Project project = projectRepository.findProjectWithChapters(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getSessionId().equals(sessionId)) {
            throw new RuntimeException("Unauthorized");
        }

        // force loading template data
        project.getBeatTemplate().getBeatSegments().size();

        return project;
    }
}