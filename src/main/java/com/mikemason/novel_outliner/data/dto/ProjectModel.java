package com.mikemason.novel_outliner.data.dto;

public record ProjectModel(String projectTitle, int targetWordCount, String beatTemplateName, Long selectedTemplateId) {
    
}
