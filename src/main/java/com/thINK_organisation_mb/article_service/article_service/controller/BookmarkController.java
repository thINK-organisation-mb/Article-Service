package com.thINK_organisation_mb.article_service.article_service.controller;

import com.thINK_organisation_mb.article_service.article_service.entity.Bookmark;
import com.thINK_organisation_mb.article_service.article_service.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/{articleId}/bookmark")
    public ResponseEntity<Bookmark> bookmarkArticle(
            @PathVariable UUID articleId,
            @RequestParam UUID userId) {
        Bookmark bookmark = bookmarkService.bookmarkArticle(userId, articleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookmark);
    }

    @GetMapping("/users/{userId}/bookmarks")
    public ResponseEntity<List<Bookmark>> getBookmarksByUser(@PathVariable UUID userId) {
        List<Bookmark> bookmarks = bookmarkService.getBookmarksByUser(userId);
        return ResponseEntity.ok(bookmarks);
    }

    @DeleteMapping("/{articleId}/bookmark")
    public ResponseEntity<Void> removeBookmark(
            @PathVariable UUID articleId,
            @RequestParam UUID userId) {
        bookmarkService.removeBookmark(userId, articleId);
        return ResponseEntity.noContent().build();
    }
}