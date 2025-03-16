package com.thINK_organisation_mb.article_service.article_service.service;

import com.thINK_organisation_mb.article_service.article_service.entity.Article;
import com.thINK_organisation_mb.article_service.article_service.entity.Bookmark;
import com.thINK_organisation_mb.article_service.article_service.repository.BookmarkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;


    @Transactional
    public Bookmark bookmarkArticle(UUID userId, UUID articleId) {
        Bookmark bookmark = Bookmark.builder()
                .userId(userId)
                .article(Article.builder().aid(articleId).build()) // Set only the article ID
                .build();
        return bookmarkRepository.save(bookmark);
    }

    @Transactional
    public Bookmark getBookmark(UUID userId, UUID articleId) {
        Bookmark bookmark = bookmarkRepository.findByUserIdAndArticle_Aid(userId, articleId);
        return bookmark;
    }


    @Transactional
    public List<Bookmark> getBookmarksByUser(UUID userId) {
        return bookmarkRepository.findByUserId(userId);
    }


    @Transactional
    public void removeBookmark(UUID userId, UUID articleId) {
        bookmarkRepository.deleteByUserIdAndArticle_Aid(userId, articleId);
    }
}