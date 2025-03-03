package com.thINK_organisation_mb.article_service.article_service.repository;

import com.thINK_organisation_mb.article_service.article_service.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {
    List<Article> findByUid(UUID uid);
    List<Article> findByTopic_TopicId(Long topicId);
    @Query("SELECT a FROM Article a WHERE LOWER(a.articleName) LIKE LOWER(concat('%', :query, '%')) OR LOWER(CAST(a.content AS string)) LIKE LOWER(concat('%', :query, '%'))")
    List<Article> findByArticleNameContainingIgnoreCaseOrContentContainingIgnoreCase(@Param("query") String query);
    List<Article> findByPublishedAndTopic_TopicNameContainingIgnoreCaseAndReadTimeEstimateBetween(boolean published, String topicName, int minReadTime, int maxReadTime);
    List<Article> findByPublishedAndTopic_TopicNameContainingIgnoreCase(boolean published, String topicName);
    List<Article> findByPublishedAndReadTimeEstimateBetween(boolean published, int minReadTime, int maxReadTime);
    List<Article> findByTopic_TopicNameContainingIgnoreCaseAndReadTimeEstimateBetween(String topicName, int minReadTime, int maxReadTime);
    List<Article> findByPublished(boolean published);
    List<Article> findByTopic_TopicNameContainingIgnoreCase(String topicName);
    List<Article> findByReadTimeEstimateBetween(int minReadTime, int maxReadTime);
}