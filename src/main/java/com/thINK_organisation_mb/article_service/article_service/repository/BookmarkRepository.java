package com.thINK_organisation_mb.article_service.article_service.repository;

import com.thINK_organisation_mb.article_service.article_service.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, UUID> {


    @Query("SELECT b FROM Bookmark b JOIN FETCH b.article a WHERE b.userId = :userId")
    List<Bookmark> findByUserId(UUID userId);


    @Query("SELECT b FROM Bookmark b JOIN FETCH b.article a WHERE b.userId = :userId AND a.aid = :articleId")
    Bookmark findByUserIdAndArticle_Aid(UUID userId, UUID articleId); // Use "Article_Aid" to reference the article's ID

    void deleteByUserIdAndArticle_Aid(UUID userId, UUID articleId); // Use "Article_Aid" to reference the article's ID
}