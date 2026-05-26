package com.example.excuse.dto;

import com.example.excuse.entity.CommentEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private Long id;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponse(CommentEntity comment) {
        this.id = comment.getId();
        this.nickname = comment.getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();

    }
}