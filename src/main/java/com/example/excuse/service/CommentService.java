package com.example.excuse.service;

import com.example.excuse.dto.CommentRequest;
import com.example.excuse.dto.CommentResponse;
import com.example.excuse.entity.CommentEntity;
import com.example.excuse.entity.ExcuseEntity;
import com.example.excuse.repository.CommentRepository;
import com.example.excuse.repository.ExcuseRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ExcuseRepository excuseRepository;

    @Transactional
    public CommentResponse addComment(Long excuseId, CommentRequest requestDto) {
        ExcuseEntity excuse = excuseRepository.findById(excuseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid excuse ID"));

        CommentEntity comment = CommentEntity.builder()
                .excuse(excuse)
                .nickname(requestDto.getNickname())
                .content(requestDto.getContent())
                .build();

        CommentEntity saved = commentRepository.save(comment);
        return new CommentResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long excuseId) {
        return commentRepository.findAllByExcuseIdOrderByCreatedAtDesc(excuseId).stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse updateComment(Long excuseId, Long commentId, CommentRequest requestDto) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));

        if (!comment.getExcuse().getId().equals(excuseId)) {
            throw new IllegalArgumentException("Comment does not belong to the excuse");
        }

        comment.setContent(requestDto.getContent());
        // Swagger says nickname is in CommentRequestDto, but typically we don't change nickname on update.
        // However, if the user sends it, we could update it if requested. 
        // For now, I'll just update content as is common.
        if (requestDto.getNickname() != null) {
            comment.setNickname(requestDto.getNickname());
        }

        return new CommentResponse(comment);
    }

    @Transactional
    public void deleteComment(Long excuseId, Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));

        if (!comment.getExcuse().getId().equals(excuseId)) {
            throw new IllegalArgumentException("Comment does not belong to the excuse");
        }

        commentRepository.delete(comment);
    }
}