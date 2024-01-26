package com.example.demo.repository;

import com.example.demo.entity.AnimalKeywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalKeywordsRepository extends JpaRepository<AnimalKeywords, Long> {
    List<AnimalKeywords> findAllByAnimalId(int animalId);
    List<AnimalKeywords> findAllByOptionId(int optionId);
}
