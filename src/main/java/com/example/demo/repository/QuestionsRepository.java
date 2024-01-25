package com.example.demo.repository;

import com.example.demo.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    Optional<Questions> findByQuestionId(int questionId);

}