package com.example.excuse.repository;


import com.example.excuse.entity.ExcuseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcuseRepository extends JpaRepository<ExcuseEntity, Long> {
    List<ExcuseEntity> findAllByOrderByCreatedAtDesc();
    List<ExcuseEntity> findTop3ByOrderByLikesDescCreatedAtDesc();
}