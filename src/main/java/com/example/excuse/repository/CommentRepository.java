package com.example.excuse.repository;

import com.example.excuse.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByExcuseIdOrderByCreatedAtDesc(Long excuseId);
}