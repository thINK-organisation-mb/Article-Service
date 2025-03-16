package com.thINK_organisation_mb.article_service.article_service.controller;

import com.thINK_organisation_mb.article_service.article_service.entity.Bookmark;
import com.thINK_organisation_mb.article_service.article_service.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @PostMapping("/{articleId}/{userId}/bookmark")
    public ResponseEntity<Bookmark> bookmarkArticle(
            @PathVariable UUID articleId,
            @PathVariable UUID userId) {
        Bookmark bookmark = bookmarkService.bookmarkArticle(userId, articleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookmark);
    }

    @GetMapping("/users/{userId}/bookmarks")
    public ResponseEntity<List<Bookmark>> getBookmarksByUser(@PathVariable UUID userId) {
        List<Bookmark> bookmarks = bookmarkService.getBookmarksByUser(userId);
        return ResponseEntity.ok(bookmarks);
    }

    @GetMapping("/users/{userId}/{articleId}")
    public ResponseEntity<Bookmark> getBookmark(@PathVariable UUID userId, @PathVariable UUID articleId){
        Bookmark bookmark = bookmarkService.getBookmark(userId, articleId);
        return ResponseEntity.ok(bookmark);
    }

    @DeleteMapping("/{articleId}/{userId}/bookmark")
    public ResponseEntity<Void> removeBookmark(
            @PathVariable UUID articleId,
            @PathVariable UUID userId) {
        bookmarkService.removeBookmark(userId, articleId);
        return ResponseEntity.noContent().build();
    }
}