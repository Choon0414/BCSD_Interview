package com.example.demo.controller;

import com.example.demo.DTO.UserKeywordsDTO;
import com.example.demo.entity.Questions;
import com.example.demo.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService){
        this.surveyService = surveyService;
    }

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
    public String getOptionsList(@RequestBody List<UserKeywordsDTO> userKeywordsList,@PathVariable String user_id) {
        for (UserKeywordsDTO userKeywordsDTO : userKeywordsList) {
            surveyService.keywordsRegister(userKeywordsDTO, user_id);
        }
        return "/{user_id}/match 링크로 이동 하고싶은데..";
    }
}