package com.thINK_organisation_mb.article_service.article_service.repository;

import com.thINK_organisation_mb.article_service.article_service.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID>{
    List<Article> findByMemberOnly(boolean memberOnly);
    List<Article> findByTopic_TopicId(Long topicId);
    List<Article> findByUid(UUID uid);
}
