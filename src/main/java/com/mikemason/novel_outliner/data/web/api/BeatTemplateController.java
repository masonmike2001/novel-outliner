//package com.mikemason.novel_outliner.data.web.api;
//
//import com.mikemason.novel_outliner.data.entities.BeatTemplate;
//import com.mikemason.novel_outliner.data.entities.Project;
//import com.mikemason.novel_outliner.data.services.BeatTemplateService;
//import com.mikemason.novel_outliner.data.services.ProjectService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class BeatTemplateController {
//
//    private final BeatTemplateService beatTemplateService;
//
//    public BeatTemplateController(BeatTemplateService beatTemplateService) {
//        this.beatTemplateService = beatTemplateService;
//    }
//
//    @GetMapping("/templates")
//    public List<BeatTemplate> getBeatTemplates() {
//
//        return beatTemplateService.getBeatTemplates();
//    }
//
////    @PostMapping
////    public Project createBeatTemplate() {
////        return ProjectService.save(project, session.getId());
////    }
//
//    @DeleteMapping("/templates/{id}")
//    public ResponseEntity<Void> deleteBeatTemplate(
//            @PathVariable Long id
//    ) {
//
//        beatTemplateService.deleteBeatTemplate(id);
//
//        return ResponseEntity.noContent().build();
//    }
//}