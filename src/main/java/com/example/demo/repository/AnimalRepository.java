package com.example.demo.repository;

import com.example.demo.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByAnimalName(String animalName); // 동물을 animal_name(String)으로 찾기 위한 메소드
    Optional<Animal> findByAnimalId(int animalId);
}