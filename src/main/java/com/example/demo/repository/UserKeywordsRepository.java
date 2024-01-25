package com.example.demo.repository;

import com.example.demo.entity.UserKeywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserKeywordsRepository extends JpaRepository<UserKeywords, Long> {
    Optional<UserKeywords> findByUserId(String userId);
}
