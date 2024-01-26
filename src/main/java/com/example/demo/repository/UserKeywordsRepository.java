package com.example.demo.repository;

import com.example.demo.entity.UserKeywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserKeywordsRepository extends JpaRepository<UserKeywords, Long> {
    List<UserKeywords> findAllByUserId(String userId);
}
