package com.thINK_organisation_mb.article_service.article_service.controller;

import com.thINK_organisation_mb.article_service.article_service.entity.Article;
import com.thINK_organisation_mb.article_service.article_service.service.ArticleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    // Constructor injection
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

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

    @Transactional
    @GetMapping("/user/{uid}")
    public List<Article> getArticlesByUser(@PathVariable UUID uid) {
        return articleService.getArticlesByUser(uid);
    }

    @Transactional
    @GetMapping("/topic/{topicId}")
    public List<Article> getArticlesByTopic(@PathVariable Long topicId) {
        return articleService.getArticlesByTopic(topicId);
    }

    @Transactional
    @PutMapping("/{id}/make-public")
    public Article makeArticlePublic(@PathVariable UUID id) {
        return articleService.makeArticlePublic(id);
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable UUID id) {
        articleService.deleteArticle(id);
    }
}