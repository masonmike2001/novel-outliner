package com.mikemason.novel_outliner.data.web.api;

import com.mikemason.novel_outliner.data.entities.BeatSegment;
import com.mikemason.novel_outliner.data.entities.BeatTemplate;
import com.mikemason.novel_outliner.data.services.BeatSegmentService;
import com.mikemason.novel_outliner.data.services.BeatTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BeatSegmentController {

    private final BeatSegmentService beatSegmentService;

    public BeatSegmentController(BeatSegmentService beatSegmentService) {
        this.beatSegmentService = beatSegmentService;
    }

    @GetMapping("/segments")
    public List<BeatSegment> getBeatSegments() {

        return beatSegmentService.getBeatSegments();
    }

//    @PostMapping
//    public Project createBeatTemplate() {
//        return ProjectService.save(project, session.getId());
//    }

    @DeleteMapping("/segments/{id}")
    public ResponseEntity<Void> deleteBeatSegment(
            @PathVariable Long id
    ) {

        beatSegmentService.deleteBeatSegment(id);

        return ResponseEntity.noContent().build();
    }
}