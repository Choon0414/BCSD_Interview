package com.example.demo.repository;

import com.example.demo.entity.Animal;
import com.example.demo.entity.AnimalKeywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalKeywordsRepository extends JpaRepository<AnimalKeywords, Long> {
    List<AnimalKeywords> findByAnimalId(int optionId); // optionId로 animalId 찾기

}
