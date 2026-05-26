package com.example.excuse.dto;

import com.example.excuse.entity.ExcuseEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExcuseResponse {

    private Long id;
    private String nickname;
    private String content;
    private String category;
    private String emotionTag;
    private int likes;
    private LocalDateTime createdAt;

    public ExcuseResponse(ExcuseEntity excuse) {
        this.id = excuse.getId();
        this.nickname = excuse.getNickname();
        this.content = excuse.getContent();
        this.category = excuse.getCategory();
        this.emotionTag = excuse.getEmotionTag();
        this.likes = excuse.getLikes();
        this.createdAt = excuse.getCreatedAt();
    }
}
