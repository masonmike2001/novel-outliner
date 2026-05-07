package com.mikemason.novel_outliner.data.services;



import com.mikemason.novel_outliner.data.entities.Project;
import com.mikemason.novel_outliner.data.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private static ProjectRepository projectRepository = null;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public static Project save(Project project, String sessionId) {
        projectRepository.findBySessionIdOrderById(sessionId);
        project.setSessionId(sessionId);
        return projectRepository.save(project);
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
}