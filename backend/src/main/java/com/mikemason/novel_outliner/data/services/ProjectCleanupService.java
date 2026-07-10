package com.mikemason.novel_outliner.data.services;

import com.mikemason.novel_outliner.data.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProjectCleanupService {

    private final ProjectRepository projectRepository;

    public ProjectCleanupService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Scheduled(fixedRate = 3600000) // every hour
    @Transactional
    public void cleanupOldSessions() {

        LocalDateTime cutoff = LocalDateTime.now().minusHours(2);

        projectRepository.deleteByCreatedAtBefore(cutoff);
    }
}