package com.thINK_organisation_mb.article_service.article_service.service;

import com.thINK_organisation_mb.article_service.article_service.entity.Article;
import com.thINK_organisation_mb.article_service.article_service.entity.Topic;
import com.thINK_organisation_mb.article_service.article_service.repository.ArticleRepository;
import com.thINK_organisation_mb.article_service.article_service.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TopicRepository topicRepository;

    public Article createArticle(Article article) {
        // Check if the topic already exists
        Topic topic = article.getTopic();
        if (topic.getTopicId() == null) {
            // If the topic is new, save it first
            topic = topicRepository.save(topic);
        } else {
            // If the topic exists, fetch it from the database
            topic = topicRepository.findById(topic.getTopicId())
                    .orElseThrow(() -> new RuntimeException("Topic not found"));
        }

        // Set the managed topic back to the article
        article.setTopic(topic);

        // Save the article
        return articleRepository.save(article);
    }

    public Optional<Article> getArticleById(UUID id) {
        return articleRepository.findById(id);
    }

    public List<Article> getArticlesByUser(UUID uid) {
        return articleRepository.findByUid(uid);
    }

    public List<Article> getArticlesByTopic(Long topicId) {
        return articleRepository.findByTopic_TopicId(topicId);
    }

    public void deleteArticle(UUID id) {
        articleRepository.deleteById(id);
    }

    public Article makeArticlePublic(UUID id) {
        Article article = articleRepository.findById(id).orElseThrow();
        article.setMemberOnly(false);
        return articleRepository.save(article);
    }
}
