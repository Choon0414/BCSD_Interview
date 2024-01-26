package com.example.demo.controller;

import com.example.demo.DTO.AnimalDTO;
import com.example.demo.DTO.OptionDTO;
import com.example.demo.service.MatchingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


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
    public Set<AnimalDTO> matchingAnimals(@PathVariable String user_id) {
        Set<AnimalDTO> animalList = matchingService.getAnimalDTOList(matchingService.getOptionsDTOList(user_id));

        return animalList;
    }
}
