package com.example.excuse.controller;

import com.example.excuse.dto.CommentRequest;
import com.example.excuse.dto.CommentResponse;
import com.example.excuse.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/excuses/{excuseId}/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long excuseId, @RequestBody CommentRequest requestDto) {
        return ResponseEntity.ok(commentService.addComment(excuseId, requestDto));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long excuseId) {
        return ResponseEntity.ok(commentService.getComments(excuseId));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long excuseId,
            @PathVariable Long commentId,
            @RequestBody CommentRequest requestDto) {
        return ResponseEntity.ok(commentService.updateComment(excuseId, commentId, requestDto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long excuseId,
            @PathVariable Long commentId) {
        commentService.deleteComment(excuseId, commentId);
        return ResponseEntity.noContent().build();
    }
}