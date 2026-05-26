package com.example.excuse.repository;

import com.example.excuse.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    Optional<VoteEntity> findFirstByActiveOrderByCreatedAtDesc(boolean active);
}
