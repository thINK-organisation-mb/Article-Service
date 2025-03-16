package com.thINK_organisation_mb.article_service.article_service.controller;

import com.thINK_organisation_mb.article_service.article_service.dto.ArticleDTO;
import com.thINK_organisation_mb.article_service.article_service.dto.ArticleMainDTO;
import com.thINK_organisation_mb.article_service.article_service.dto.ArticleUserDTO;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article createArticle(@RequestBody ArticleDTO articleDTO) {
        Article newArticle = new Article();
        newArticle.setArticleName(articleDTO.getArticleName());
        newArticle.setAuthorEmail(articleDTO.getAuthorEmail());
        newArticle.setUid(articleDTO.getUid());
        newArticle.setPreview(articleDTO.getPreview());
        newArticle.setContent(articleDTO.getContent());
        newArticle.setTopic(articleDTO.getTopic());
        newArticle.setRt_estimate(articleDTO.getRt_estimate());
        newArticle.setDate(articleDTO.getDate());
        return articleService.createArticle(newArticle);
    }

    @GetMapping
    public List<ArticleMainDTO> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("user/{userId}/article/{articleId}")
    public ArticleUserDTO getArticleForUser(@PathVariable UUID userId, @PathVariable UUID articleId){
        return articleService.getArticleForUser(userId, articleId);
    }

    @GetMapping("user/{userId}/bookmark")
    public List<ArticleUserDTO> getArticleForUserBookmarked(@PathVariable UUID userId){
        return articleService.getArticleForUserBookmarked(userId);
    }

    @GetMapping("user/{userId}/followed")
    public List<ArticleUserDTO> getArticleForUserFollowed(@PathVariable UUID userId){
        return articleService.getArticleForUserFollowed(userId);
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable UUID id) {
        return articleService.getArticleById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
    }

    @Transactional
    @PutMapping("/{id}")
    public Article editArticle(@PathVariable UUID id, @RequestBody Article article) {
        return articleService.editArticle(id, article);
    }

    @Transactional
    @GetMapping("/user/{userId}")
    public List<ArticleUserDTO> getArticlesByUser(@PathVariable UUID userId) {
        return articleService.getArticlesByUser(userId);
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable UUID id) {
        articleService.deleteArticle(id);
    }
}