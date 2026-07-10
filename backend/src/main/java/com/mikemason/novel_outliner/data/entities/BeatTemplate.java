package com.mikemason.novel_outliner.data.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="beat_template")
public class BeatTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="projectTitle")
    private String title; // "Three Act Structure", "Hero's Journey"

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequenceOrder ASC")
    @JsonManagedReference
    private List<BeatSegment> beatSegments = new ArrayList<>();

    @Column(name="summary")
    private String summary; // "Three Act Structure", "Hero's Journey"
}