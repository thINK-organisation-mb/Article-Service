package com.thINK_organisation_mb.article_service.article_service.service;

import com.thINK_organisation_mb.article_service.article_service.client.ReactionClient;
import com.thINK_organisation_mb.article_service.article_service.client.UserClient;
import com.thINK_organisation_mb.article_service.article_service.dto.ArticleMainDTO;
import com.thINK_organisation_mb.article_service.article_service.dto.ArticleUserDTO;
import com.thINK_organisation_mb.article_service.article_service.dto.ReactionDTO;
import com.thINK_organisation_mb.article_service.article_service.entity.Article;
import com.thINK_organisation_mb.article_service.article_service.entity.Bookmark;
import com.thINK_organisation_mb.article_service.article_service.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    private BookmarkService bookmarkService;

    private ReactionClient reactionClient;

    @Autowired
    public void UserService(ReactionClient reactionClient) {
        this.reactionClient = reactionClient;
    }

    private UserClient userClient;

    @Autowired
    public void UserService(UserClient userClient) {
        this.userClient = userClient;
    }


    // Constructor injection
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Optional<Article> getArticleById(UUID id) {
        return articleRepository.findById(id);
    }

    public List<ArticleUserDTO> getArticlesByUser(UUID uid) {
        List<Article> articles = articleRepository.findByUid(uid);
        List<ArticleUserDTO> result = new ArrayList<>();
        for(Article article : articles) {
            ArticleUserDTO r = new ArticleUserDTO();
            r.setAid(article.getAid());
            r.setArticleName(article.getArticleName());
            r.setPreview(article.getPreview());
            r.setContent(article.getContent());
            r.setTopic(article.getTopic());
            r.setRt_estimate(article.getRt_estimate());
            r.setUid(article.getUid());
            r.setDate(article.getDate());
            r.setAuthorName(userClient.getUser(article.getUid()).getBody().getUsername());
            Bookmark bookmark = bookmarkService.getBookmark(uid, article.getAid());
            if(bookmark == null) r.setIsBookmarked(false);
            r.setIsBookmarked(true);
            r.setAuthorEmail(article.getAuthorEmail());
            r.setComments(reactionClient.getCommentsByArticleId(article.getAid()).size());
            r.setLikes(reactionClient.getReactionsByArticleId(article.getAid()).size());
            r.setIsLiked(reactionClient.getReactionByUserIdAndArticleId(uid, article.getAid()).getBody());
            result.add(r);
        }
        return result;
    }

    public void deleteArticle(UUID id) {
        articleRepository.deleteById(id);
    }

    // New method to edit an article
    public Article editArticle(UUID id, Article updatedArticle) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        existingArticle.setArticleName(updatedArticle.getArticleName());
        existingArticle.setContent(updatedArticle.getContent());
        existingArticle.setDate(updatedArticle.getDate());
        existingArticle.setPreview(updatedArticle.getPreview());
        existingArticle.setRt_estimate(updatedArticle.getRt_estimate());
        existingArticle.setTopic(updatedArticle.getTopic());

        return articleRepository.save(existingArticle);
    }

    public List<ArticleMainDTO> getArticles(){
        List<Article> articles = articleRepository.findAll();
        List<ArticleMainDTO> result = new ArrayList<>();
        for(Article article : articles){
            ArticleMainDTO r = new ArticleMainDTO();
            r.setAid(article.getAid());
            r.setArticleName(article.getArticleName());
            r.setPreview(article.getPreview());
            r.setContent(article.getContent());
            r.setTopic(article.getTopic());
            r.setRt_estimate(article.getRt_estimate());
            r.setUid(article.getUid());
            r.setDate(article.getDate());
            r.setAuthorName(userClient.getUser(article.getUid()).getBody().getUsername());
            r.setAuthorEmail(article.getAuthorEmail());
            r.setComments(reactionClient.getCommentsByArticleId(article.getAid()).size());
            r.setLikes(reactionClient.getReactionsByArticleId(article.getAid()).size());
            result.add(r);
        }
        return result;
    }

    public List<ArticleUserDTO> getArticleForUserFollowed(UUID userId){
        List<Article> articles = articleRepository.findAll();
        List<ArticleUserDTO> result = new ArrayList<>();
        for(Article article : articles) {
            if(Boolean.FALSE.equals(userClient.follows(userId, article.getUid()).getBody())) continue;
            ArticleUserDTO r = new ArticleUserDTO();
            r.setAid(article.getAid());
            r.setArticleName(article.getArticleName());
            r.setPreview(article.getPreview());
            r.setContent(article.getContent());
            r.setTopic(article.getTopic());
            r.setRt_estimate(article.getRt_estimate());
            r.setUid(article.getUid());
            r.setDate(article.getDate());
            r.setAuthorName(userClient.getUser(article.getUid()).getBody().getUsername());
            Bookmark bookmark = bookmarkService.getBookmark(userId, article.getAid());
            if(bookmark == null) r.setIsBookmarked(false);
            r.setIsBookmarked(true);
            r.setAuthorEmail(article.getAuthorEmail());
            r.setComments(reactionClient.getCommentsByArticleId(article.getAid()).size());
            r.setLikes(reactionClient.getReactionsByArticleId(article.getAid()).size());
            r.setIsLiked(reactionClient.getReactionByUserIdAndArticleId(userId, article.getAid()).getBody());
            result.add(r);
        }
        return result;
    }

    public List<ArticleUserDTO> getArticleForUserBookmarked(UUID userId){
        List<Article> articles = articleRepository.findAll();
        List<ArticleUserDTO> result = new ArrayList<>();
        for(Article article : articles) {
            ArticleUserDTO r = new ArticleUserDTO();
            r.setAid(article.getAid());
            r.setArticleName(article.getArticleName());
            r.setPreview(article.getPreview());
            r.setContent(article.getContent());
            r.setTopic(article.getTopic());
            r.setRt_estimate(article.getRt_estimate());
            r.setUid(article.getUid());
            r.setDate(article.getDate());
            r.setAuthorName(userClient.getUser(article.getUid()).getBody().getUsername());
            Bookmark bookmark = bookmarkService.getBookmark(userId, article.getAid());
            if(bookmark == null) continue;
            r.setIsBookmarked(true);
            r.setAuthorEmail(article.getAuthorEmail());
            r.setComments(reactionClient.getCommentsByArticleId(article.getAid()).size());
            r.setLikes(reactionClient.getReactionsByArticleId(article.getAid()).size());
            r.setIsLiked(reactionClient.getReactionByUserIdAndArticleId(userId, article.getAid()).getBody());
            result.add(r);
        }
        return result;
    }

    public ArticleUserDTO getArticleForUser(UUID userId, UUID articleId){
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (!optionalArticle.isPresent()) {
            return null;
        }
        Article article = optionalArticle.get();
        ArticleUserDTO r = new ArticleUserDTO();
        r.setAid(article.getAid());
        r.setArticleName(article.getArticleName());
        r.setPreview(article.getPreview());
        r.setContent(article.getContent());
        r.setTopic(article.getTopic());
        r.setRt_estimate(article.getRt_estimate());
        r.setUid(article.getUid());
        r.setDate(article.getDate());
        r.setAuthorName(userClient.getUser(article.getUid()).getBody().getUsername());
        Bookmark bookmark = bookmarkService.getBookmark(userId, articleId);
        r.setIsBookmarked(bookmark != null);
        r.setAuthorEmail(article.getAuthorEmail());
        r.setComments(reactionClient.getCommentsByArticleId(article.getAid()).size());
        r.setLikes(reactionClient.getReactionsByArticleId(article.getAid()).size());
        r.setIsLiked(reactionClient.getReactionByUserIdAndArticleId(userId, articleId).getBody());
        return r;
    }

}