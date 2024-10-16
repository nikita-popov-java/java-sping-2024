package ru.nikitapopov.skillbox.mod4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikitapopov.skillbox.mod4.CheckCommentOwnership;
import ru.nikitapopov.skillbox.mod4.dto.CommentDTO;
import ru.nikitapopov.skillbox.mod4.service.CommentService;

import java.util.List;

//@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/news/{newsId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByNewsId(@PathVariable Long newsId) {
        return ResponseEntity.ok(commentService.getCommentsByNewsId(newsId));
    }

    @PostMapping("/news/{newsId}")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long newsId, @Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.createComment(newsId, commentDTO));
    }

    @PutMapping("/{commentId}")
    @CheckCommentOwnership
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.updateComment(commentId, commentDTO));
    }

    @DeleteMapping("/{commentId}")
    @CheckCommentOwnership
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
