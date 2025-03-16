package com.thINK_organisation_mb.article_service.article_service.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDTO {

    private String articleName;
    private String authorEmail;
    private UUID uid;
    private String preview;
    private String content;
    private String topic;
    private int rt_estimate;
    private Date date;
}