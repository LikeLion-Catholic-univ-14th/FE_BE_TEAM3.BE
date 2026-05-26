package com.example.excuse.service;

import com.example.excuse.dto.VoteResponse;
import com.example.excuse.entity.VoteEntity;
import com.example.excuse.repository.VoteRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    @Transactional(readOnly = true)
    public VoteResponse getCurrentVote() {
        VoteEntity vote = voteRepository.findFirstByActiveOrderByCreatedAtDesc(true)
                .orElse(null);
        return vote != null ? new VoteResponse(vote) : null;
    }

    @Transactional
    public void castVote(Long id, String option) {
        VoteEntity vote = voteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vote ID"));

        if ("A".equalsIgnoreCase(option)) {
            vote.voteA();
        } else if ("B".equalsIgnoreCase(option)) {
            vote.voteB();
        } else {
            throw new IllegalArgumentException("Invalid option");
        }
    }

    @Transactional
    public VoteEntity createvote(String title, String optionA, String optionB) {
        VoteEntity vote = VoteEntity.builder()
                .title(title)
                .optionA(optionA)
                .optionB(optionB)
                .active(true)
                .build();
        return voteRepository.save(vote);
    }
}
