package com.example.demo.service;

import com.example.demo.DTO.UserKeywordsDTO;
import com.example.demo.entity.Questions;
import com.example.demo.entity.UserKeywords;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.repository.UserKeywordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final QuestionsRepository questionRepository;
    private final UserKeywordsRepository userKeywordsRepository;

    // 질문 모두 불러오기
    public List<Questions> getAllQuestions() {
        return questionRepository.findAll();
    }

    // 사용자의 키워드 저장하기
    public String keywordsRegister(UserKeywordsDTO dto) {
        UserKeywords userKeywords = UserKeywords.builder()
                .userId(dto.getUserId())
                .optionId(dto.getOptionId())
                .build();
        return userKeywordsRepository.save(userKeywords).getUserId();
    }


}