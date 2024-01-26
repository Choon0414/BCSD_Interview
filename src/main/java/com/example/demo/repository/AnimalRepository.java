package com.example.demo.repository;

import com.example.demo.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByAnimalName(String animalName);
    Optional<Animal> findByAnimalId(int animalId);
}