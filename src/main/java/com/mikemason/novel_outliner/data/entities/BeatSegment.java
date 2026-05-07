package com.mikemason.novel_outliner.data.entities;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name="beat-segment", schema = "templates")
public class BeatSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name; // "Act 1", "Act 2", etc.

    @Column(name = "start_percentage")
    private Double startPercentage; // 0.0

    @Column(name = "end_percentage")
    private Double endPercentage;   // 0.33

    @Column(name = "sequence_order")
    private Integer sequenceOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private BeatTemplate template;

    public BeatSegment() {}
}