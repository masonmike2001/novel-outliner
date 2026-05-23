package com.mikemason.novel_outliner.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_title")
    private String title;

    @Column(name = "target_total_word_count")
    private Integer targetTotalWordCount;




    @JsonIgnoreProperties
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beat_template_id", nullable = true)
    private BeatTemplate beatTemplate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @OrderBy("sequenceOrder ASC")
    private List<Chapter> chapters = new ArrayList<>();

    private String sessionId;

    @Column(name = "create_date")
    private LocalDateTime createdAt;

    @Column(name = "target_chapter_word_count")
    private Integer chapterLength;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}