package com.mikemason.novel_outliner.data.services;



import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import com.mikemason.novel_outliner.data.entities.Project;
import com.mikemason.novel_outliner.data.repositories.BeatTemplateRepository;
import com.mikemason.novel_outliner.data.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeatTemplateService {
    private final BeatTemplateRepository beatTemplateRepository;
    @Autowired
    public BeatTemplateService(BeatTemplateRepository beatTemplateRepository) {
        this.beatTemplateRepository = beatTemplateRepository;
    }

    public BeatTemplate save(BeatTemplate beatTemplate) {
        return beatTemplateRepository.save(beatTemplate);
    }

    public List<BeatTemplate> getBeatTemplates() {
        return beatTemplateRepository.findAll();
    }

    public void deleteBeatTemplate(Long id) {
        BeatTemplate beatTemplate = beatTemplateRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Segment not found"));
        beatTemplateRepository.delete(beatTemplate);
    }
}