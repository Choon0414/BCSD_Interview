package com.example.demo.controller;

import com.example.demo.DTO.AnimalDTO;
import com.example.demo.DTO.OptionDTO;
import com.example.demo.service.MatchingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService){
        this.matchingService = matchingService;
    }

    // {user_id}의 키워드 출력
    @GetMapping("/{user_id}/match")
    public List<OptionDTO> showOptionsList(@PathVariable String user_id) {
        return matchingService.getOptionsDTOList(user_id);
    }

    // {user_id}의 키워드를 가지고 추천 동물 출력
    @PostMapping("/{user_id}/match")
    public ResponseEntity<Object> matchingAnimals(@PathVariable String user_id) {
        List<Integer> optionList = matchingService.getOptionsDTOList(user_id).stream()
                .map(OptionDTO::getOptionId)
                .collect(Collectors.toList());
        Set<AnimalDTO> animalList = matchingService.getAnimalDTOList(optionList);
        try {
            return new ResponseEntity<>(matchingService.sumWeights(optionList, animalList), HttpStatus.OK);
        }
        catch (NullPointerException e) {
            return new ResponseEntity<>("매칭된 동물이 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
