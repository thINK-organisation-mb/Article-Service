package com.thINK_organisation_mb.article_service.article_service.controller;

import com.thINK_organisation_mb.article_service.article_service.entity.Article;
import com.thINK_organisation_mb.article_service.article_service.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable UUID id) {
        return articleService.getArticleById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
    }

    @GetMapping("/user/{uid}")
    public List<Article> getArticlesByUser(@PathVariable UUID uid) {
        return articleService.getArticlesByUser(uid);
    }

    @GetMapping("/topic/{topicId}")
    public List<Article> getArticlesByTopic(@PathVariable Long topicId) {
        return articleService.getArticlesByTopic(topicId);
    }

    @PutMapping("/{id}/make-public")
    public Article makeArticlePublic(@PathVariable UUID id) {
        return articleService.makeArticlePublic(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable UUID id) {
        articleService.deleteArticle(id);
    }
}