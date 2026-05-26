package com.example.excuse.service;

import com.example.excuse.dto.ExcuseRequest;
import com.example.excuse.dto.ExcuseResponse;
import com.example.excuse.entity.ExcuseEntity;
import com.example.excuse.repository.ExcuseRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcuseService {

    private final ExcuseRepository excuseRepository;

    @Transactional
    public ExcuseResponse createExcuse(ExcuseRequest requestDto) {
        ExcuseEntity excuse = ExcuseEntity.builder()
                .nickname(requestDto.getNickname())
                .password(requestDto.getPassword())
                .content(requestDto.getContent())
                .category(requestDto.getCategory())
                .emotionTag(requestDto.getEmotionTag())
                .build();

        ExcuseEntity saved = excuseRepository.save(excuse);
        return new ExcuseResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ExcuseResponse> getAllExcuses() {
        return excuseRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(ExcuseResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void likeExcuse(Long id) {
        ExcuseEntity excuse = excuseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid excuse ID"));
        excuse.incrementLikes();
    }

    @Transactional(readOnly = true)
    public List<ExcuseResponse> getHallOfFame() {
        return excuseRepository.findTop3ByOrderByLikesDescCreatedAtDesc().stream()
                .map(ExcuseResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ExcuseResponse updateExcuse(Long id, ExcuseRequest requestDto) {
        ExcuseEntity excuse = excuseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid excuse ID"));

        if (!excuse.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        excuse.setContent(requestDto.getContent());
        excuse.setCategory(requestDto.getCategory());
        excuse.setEmotionTag(requestDto.getEmotionTag());

        return new ExcuseResponse(excuse);
    }

    @Transactional
    public void deleteExcuse(Long id, String password) {
        ExcuseEntity excuse = excuseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid excuse ID"));

        if (!excuse.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        excuseRepository.delete(excuse);
    }
}
