package com.example.excuse.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcuseRequest {

    private String nickname;
    private String password;
    private String content;
    private String category;
    private String emotionTag;
}
