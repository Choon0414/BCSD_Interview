package com.example.demo.controller;

import com.example.demo.DTO.UserKeywordsDTO;
import com.example.demo.entity.Animal;
import com.example.demo.entity.Options;
import com.example.demo.entity.Questions;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class SurveyController {
    private final QuestionsRepository questionRepository;
    private final SurveyService surveyService;
    private Animal animal;

    @Autowired
    public SurveyController(QuestionsRepository questionRepository, SurveyService surveyService) {
        this.questionRepository = questionRepository;
        this.surveyService = surveyService;
    }

    @GetMapping("/hyunn815/survey")
    public List<Questions> questionsList() {
        return surveyService.getAllQuestions();
    }

    @PostMapping("/hyunn815/survey")
    public String getOptionsList(@RequestBody List<UserKeywordsDTO> userKeywordsList) {
        for (UserKeywordsDTO userKeywordsDTO : userKeywordsList) {
            surveyService.keywordsRegister(userKeywordsDTO);
        }
        return "매칭 링크로 이동 하고싶은데..";
    }
}