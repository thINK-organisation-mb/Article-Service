package com.thINK_organisation_mb.article_service.article_service.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Setter
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ArticleUserDTO {
    private UUID aid;
    private String articleName;
    private String authorName;
    private String authorEmail;
    private UUID uid;
    private String preview;
    private String content;
    private String topic;
    private int rt_estimate;
    private Date date;
    private int likes;
    private int comments;
    private Boolean isLiked;
    private Boolean isBookmarked;
}
