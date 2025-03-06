package com.thINK_organisation_mb.article_service.article_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article{
    @Id
    @GeneratedValue
    private UUID aid;

    @Column(name = "articlename", nullable = false, length = 255)
    private String articleName;

    @Column(name = "uid", nullable = false)
    private UUID uid;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    @JsonBackReference
    private Topic topic;

    @Column(name = "member_only", nullable = false)
    private boolean memberOnly;

    @Column(name = "read_time_estimate", nullable = false)
    private int readTimeEstimate;

    @Column(name = "published", nullable = false)
    private boolean published;
}
