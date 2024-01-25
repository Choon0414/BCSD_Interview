package com.example.demo.controller;

import com.example.demo.DTO.UserKeywordsDTO;
import com.example.demo.entity.Animal;
import com.example.demo.entity.Options;
import com.example.demo.entity.UserKeywords;
import com.example.demo.repository.OptionsRepository;
import com.example.demo.repository.UserKeywordsRepository;
import com.example.demo.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class MatchingController {
    private final UserKeywordsRepository userKeywordsRepository;
    private final OptionsRepository optionsRepository;
    private final MatchingService matchingService;

    @Autowired
    public MatchingController(UserKeywordsRepository userKeywordsRepository, MatchingService matchingService,
                              OptionsRepository optionsRepository) {
        this.userKeywordsRepository = userKeywordsRepository;
        this.matchingService = matchingService;
        this.optionsRepository = optionsRepository;
    }

    @GetMapping("/{user_id}/match")
    public List<String> showOptionsList(@PathVariable String user_id) {
        List<UserKeywords> userKeywordsList = userKeywordsRepository.findAllByUserId(user_id);
        List<String> optionsContentList = new ArrayList<>();

        for (UserKeywords userKeywords : userKeywordsList) {
            int optionId = userKeywords.getOptionId();
            Optional<Options> options = optionsRepository.findByOptionId(optionId);
            options.ifPresent(option -> {
                optionsContentList.add(option.getContent());
            });
        }
        return optionsContentList;
    }

    @PostMapping("/{user_id}/match")
    public ResponseEntity<String> matchingAnimals(@RequestBody List<UserKeywordsDTO> userKeywordsDTOList
            , @PathVariable String user_id) {
        List<Animal> animalList = new ArrayList<>();
        List<Options> optionList = new ArrayList<>();
        matchingService.makeOptionList(optionList, user_id);
        //matchingService.makeAnimalDTO(animalList, userKeywordsDTOList);
        String matchingAnimal = matchingService.matching(animalList, optionList);
        // 최종 추천동물들 반환
        return ResponseEntity.status(HttpStatus.CREATED).body("I recommend the following animals to you!\n"
                + matchingAnimal);
    }
}
