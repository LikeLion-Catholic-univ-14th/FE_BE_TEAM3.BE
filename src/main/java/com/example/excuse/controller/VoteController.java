package com.example.excuse.controller;

import com.example.excuse.dto.VoteResponse;
import com.example.excuse.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VoteController {

    private final VoteService voteService;

    @GetMapping("/current")
    public ResponseEntity<VoteResponse> getCurrentVote() {
        VoteResponse currentVote = voteService.getCurrentVote();
        if (currentVote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(currentVote);
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<Void> castVote(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String option = body.get("option");
        voteService.castVote(id, option);
        return ResponseEntity.ok().build();
    }
}
