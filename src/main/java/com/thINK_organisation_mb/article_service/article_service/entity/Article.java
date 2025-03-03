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
    private UUID aid;  // Article ID

    @Column(name = "articlename", nullable = false, length = 255)
    private String articleName;

    @Column(name = "uid", nullable = false)
    private UUID uid;  // User ID of the author

    @Lob
    @JsonIgnore // Add this annotation
    @Column(name = "content", columnDefinition = "TEXT")
    private String content; // Storing dynamic HTML content

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    @JsonBackReference  // Prevents serialization of this side of the relationship
    private Topic topic;  // Relationship with Topic entity

    @Column(name = "member_only", nullable = false)
    private boolean memberOnly;  // Boolean flag for premium content

    @Column(name = "read_time_estimate", nullable = false)
    private int readTimeEstimate;  // Estimated read time
}
