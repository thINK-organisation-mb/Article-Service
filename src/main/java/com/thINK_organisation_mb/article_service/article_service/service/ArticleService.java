package com.thINK_organisation_mb.article_service.article_service.service;

import com.thINK_organisation_mb.article_service.article_service.entity.Article;
import com.thINK_organisation_mb.article_service.article_service.entity.Topic;
import com.thINK_organisation_mb.article_service.article_service.repository.ArticleRepository;
import com.thINK_organisation_mb.article_service.article_service.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final TopicRepository topicRepository;

    // Constructor injection
    public ArticleService(ArticleRepository articleRepository, TopicRepository topicRepository) {
        this.articleRepository = articleRepository;
        this.topicRepository = topicRepository;
    }

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
    // New method to edit an article
    public Article editArticle(UUID id, Article updatedArticle) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        existingArticle.setArticleName(updatedArticle.getArticleName());
        existingArticle.setContent(updatedArticle.getContent());
        existingArticle.setMemberOnly(updatedArticle.isMemberOnly());
        existingArticle.setReadTimeEstimate(updatedArticle.getReadTimeEstimate());

        final Topic updatedTopic = updatedArticle.getTopic(); // Declare as final
        if (updatedTopic != null) {
            if (updatedTopic.getTopicId() == null) {
                Topic savedTopic = topicRepository.save(updatedTopic);
                existingArticle.setTopic(savedTopic);
            } else {
                Topic fetchedTopic = topicRepository.findById(updatedTopic.getTopicId())
                        .orElseThrow(() -> new RuntimeException("Topic not found with ID: " + updatedTopic.getTopicId()));
                existingArticle.setTopic(fetchedTopic);
            }
        } else {
            existingArticle.setTopic(existingArticle.getTopic());
        }

        return articleRepository.save(existingArticle);
    }

    // New method to publish an article
    public Article publishArticle(UUID id) {
        Article article = articleRepository.findById(id).orElseThrow();
        // Assuming there is a field like `published` in the Article entity
        // If not, you need to add it to the Article entity
        article.setPublished(true);
        return articleRepository.save(article);
    }

    // New method to search articles based on a query
    public List<Article> searchArticles(String query) {
        return articleRepository.findByArticleNameContainingIgnoreCaseOrContentContainingIgnoreCase(query);
    }

    // New method to filter articles based on various criteria
    public List<Article> filterArticles(Boolean published, String topic, Integer minReadTime, Integer maxReadTime) {
        if (published != null && topic != null && minReadTime != null && maxReadTime != null) {
            return articleRepository.findByPublishedAndTopic_TopicNameContainingIgnoreCaseAndReadTimeEstimateBetween(published, topic, minReadTime, maxReadTime);
        } else if (published != null && topic != null) {
            return articleRepository.findByPublishedAndTopic_TopicNameContainingIgnoreCase(published, topic);
        } else if (published != null && minReadTime != null && maxReadTime != null) {
            return articleRepository.findByPublishedAndReadTimeEstimateBetween(published, minReadTime, maxReadTime);
        } else if (topic != null && minReadTime != null && maxReadTime != null) {
            return articleRepository.findByTopic_TopicNameContainingIgnoreCaseAndReadTimeEstimateBetween(topic, minReadTime, maxReadTime);
        } else if (published != null) {
            return articleRepository.findByPublished(published);
        } else if (topic != null) {
            return articleRepository.findByTopic_TopicNameContainingIgnoreCase(topic);
        } else if (minReadTime != null && maxReadTime != null) {
            return articleRepository.findByReadTimeEstimateBetween(minReadTime, maxReadTime);
        } else {
            return articleRepository.findAll();
        }
    }
}