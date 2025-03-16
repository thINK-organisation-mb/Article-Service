package com.thINK_organisation_mb.article_service.article_service.client;

import com.thINK_organisation_mb.article_service.article_service.dto.CommentDTO;
import com.thINK_organisation_mb.article_service.article_service.dto.ReactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Component
@FeignClient(name = "reaction-service", url = "${reaction.service.url}")
public interface ReactionClient {
    @GetMapping("/api/reactions/article/{articleId}")
    List<ReactionDTO> getReactionsByArticleId(@PathVariable UUID articleId);

    @GetMapping("/api/comments/article/{articleId}")
    List<CommentDTO> getCommentsByArticleId(@PathVariable UUID articleId);

    @GetMapping("/api/reactions/user/{userId}/article/{articleId}")
    ResponseEntity<Boolean> getReactionByUserIdAndArticleId(@PathVariable UUID userId, @PathVariable UUID articleId);
}