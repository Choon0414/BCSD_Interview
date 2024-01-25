package com.example.demo.controller;

import com.example.demo.DTO.UserKeywordsDTO;
import com.example.demo.entity.Animal;
import com.example.demo.entity.Options;
import com.example.demo.service.MatchingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class MatchingController {
    private MatchingService matchingService;

    @PostMapping("/hyunn815/matching")
    public ResponseEntity<String> getOptionsList(@RequestBody List<UserKeywordsDTO> userKeywordsList) {
        List<Animal> animalList = new ArrayList<>();
        List<Options> optionList = new ArrayList<>();
        for (UserKeywordsDTO userKeywordsDTO : userKeywordsList) {
            matchingService.makeOptionList(optionList);
            matchingService.makeAnimalDTO(animalList, userKeywordsDTO);
        }
        String matchingAnimal = matchingService.matching(animalList, optionList);
        return ResponseEntity.status(HttpStatus.CREATED).body("I recommend the following animals to you!\n" + matchingAnimal);
    }
}
