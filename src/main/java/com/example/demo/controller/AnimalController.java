package com.example.demo.controller;

import com.example.demo.entity.Animal;
import com.example.demo.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    // 동물 추가하기
    @PostMapping("/animal")
    public Animal create(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    // 동물 정보 보기
    @GetMapping("/{animal_name}")
    public Animal read(@PathVariable String animal_name) {
        return animalRepository.findByAnimalName(animal_name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "animal not found"));
    }
}