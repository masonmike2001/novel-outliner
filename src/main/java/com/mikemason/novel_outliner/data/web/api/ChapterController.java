package com.mikemason.novel_outliner.data.web.api;
import com.mikemason.novel_outliner.data.services.ChapterService;
import com.mikemason.novel_outliner.data.entities.Chapter;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/chapters")
    public List<Chapter> getChapters(
            @RequestParam(required = false) String sessionId,
            HttpSession session
    ) {
        String effectiveSessionId = (sessionId != null) ? sessionId : session.getId();
        return chapterService.getChapters(effectiveSessionId);
    }

    @PostMapping
    public Chapter createChapter(
            @RequestBody Chapter chapter,
            HttpSession session
    ) {
        return chapterService.save(chapter, session.getId());
    }

    @DeleteMapping("/chapters/{id}")
    public ResponseEntity<Void> deleteChapter(
            @PathVariable Long id,
            HttpSession session
    ) {

        chapterService.deleteChapter(id, session.getId());

        return ResponseEntity.noContent().build();
    }
}