package com.example.demo.controller;

import com.example.demo.DTO.AnimalDTO;
import com.example.demo.DTO.OptionDTO;
import com.example.demo.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SessionAttributes("optionDTOList")
public class MatchingController {

    private final MatchingService matchingService;

    private List<OptionDTO> optionDTOList;

    // {user_id}의 키워드 출력
    @GetMapping("/{user_id}/match")
    public List<OptionDTO> showOptionsList(@PathVariable String user_id) {
        optionDTOList = matchingService.getOptionsDTOList(user_id);
        return optionDTOList;
    }

    // {user_id}의 키워드를 가지고 추천 동물 출력
    @PostMapping("/{user_id}/match")
    public List<AnimalDTO> matchingAnimals(@PathVariable String user_id) {
        optionDTOList = matchingService.getOptionsDTOList(user_id);
        List<AnimalDTO> animalDTOList = matchingService.getAnimalDTOLIst(optionDTOList);
        return animalDTOList;
    }
}
