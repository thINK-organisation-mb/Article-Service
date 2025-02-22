package com.thINK_organisation_mb.article_service.article_service.repository;

import com.thINK_organisation_mb.article_service.article_service.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, UUID> {
    List<Bookmark> findByUserId(UUID userId);  // Get all bookmarks by user
    void deleteByUserIdAndArticle_Aid(UUID userId, UUID articleId);  // Unbookmark article
}

