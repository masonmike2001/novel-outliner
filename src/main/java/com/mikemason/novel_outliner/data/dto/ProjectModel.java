package com.mikemason.novel_outliner.data.dto;

public record ProjectModel(String projectTitle, int targetWordCount, int chapterLength, String beatTemplateName, Long selectedTemplateId) {
    
}
