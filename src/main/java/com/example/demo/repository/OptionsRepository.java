package com.example.demo.repository;

import com.example.demo.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionsRepository extends JpaRepository<Options, Long> {
    Optional<Options> findByOptionId(int optionId);
    Optional<Options> findByContent(String content);

}
