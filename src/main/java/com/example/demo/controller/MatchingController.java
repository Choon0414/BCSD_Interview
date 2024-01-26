package com.example.demo.controller;

import com.example.demo.DTO.AnimalDTO;
import com.example.demo.DTO.OptionDTO;
import com.example.demo.service.MatchingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService){
        this.matchingService = matchingService;
    }

    // {user_id}의 키워드 출력
    @GetMapping("/{user_id}/match")
    public String showOptionsList(@PathVariable String user_id, Model model) {
        List<OptionDTO> optionsList = matchingService.getOptionsDTOList(user_id);
        model.addAttribute("optionsList", optionsList);
        return "getSurvey";
    }

    // {user_id}의 키워드를 가지고 추천 동물 출력
    @PostMapping("/{user_id}/match")
    public String matchingAnimals(@PathVariable String user_id, Model model) {
        List<Integer> optionList = matchingService.getOptionsDTOList(user_id).stream()
                .map(OptionDTO::getOptionId)
                .collect(Collectors.toList());
        Set<AnimalDTO> animalList = matchingService.getAnimalDTOList(optionList);
        try {
            model.addAttribute("animalList", matchingService.sumWeights(optionList, animalList));
            return "Matching";
        }
        catch (NullPointerException e) {
            model.addAttribute("error", "매칭된 동물이 없습니다.");
            return "error";
        }
    }
}
