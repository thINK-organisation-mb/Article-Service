package com.thINK_organisation_mb.article_service.article_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "bookmark")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bookmark {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;  // ID of the user who bookmarked

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;  // The bookmarked article
}
