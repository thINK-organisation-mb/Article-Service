package com.thINK_organisation_mb.article_service.article_service.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReactionDTO {
    private Long id;
    private UUID userId;
    private UUID articleId;
    private LocalDateTime createdAt = LocalDateTime.now();
}