package com.example.demo.controller;

import com.example.demo.DTO.UserKeywordsDTO;
import com.example.demo.entity.Animal;
import com.example.demo.entity.Questions;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class SurveyController {
    private final QuestionsRepository questionRepository;
    private final SurveyService surveyService;

    // 질문 보기(설문 조사)
    @GetMapping("/{user_id}/survey")
    public List<String> questionsList() {
        List<Questions> questionsList = surveyService.getAllQuestions();
        List<String> contentList = new ArrayList<>();

        for (Questions question : questionsList) {
            String content = question.getContent();
            contentList.add(content);
        }
        return contentList;
    }

    // {user_id}가 선택한 키워드들 저장
    @PostMapping("/{user_id}/survey")
    public String getOptionsList(@RequestBody List<UserKeywordsDTO> userKeywordsList) {
        for (UserKeywordsDTO userKeywordsDTO : userKeywordsList) {
            surveyService.keywordsRegister(userKeywordsDTO);
        }
        return "/{user_id}/match 링크로 이동 하고싶은데..";
    }
}