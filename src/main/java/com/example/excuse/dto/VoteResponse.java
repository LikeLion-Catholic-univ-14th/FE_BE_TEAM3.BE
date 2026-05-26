package com.example.excuse.dto;

import com.example.excuse.entity.VoteEntity;
import lombok.Getter;

@Getter
public class VoteResponse {

    private Long id;
    private String title;
    private String optionA;
    private String optionB;
    private int countA;
    private int countB;

    public VoteResponse(VoteEntity vote) {
        this.id = vote.getId();
        this.title = vote.getTitle();
        this.optionA = vote.getOptionA();
        this.optionB = vote.getOptionB();
        this.countA = vote.getCountA();
        this.countB = vote.getCountB();
    }
}
