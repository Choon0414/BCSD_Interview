package com.example.demo.service;

import com.example.demo.DTO.UserKeywordsDTO;
import com.example.demo.entity.Animal;
import com.example.demo.entity.Options;
import com.example.demo.entity.Questions;
import com.example.demo.entity.UserKeywords;
import com.example.demo.repository.AnimalKeywordsRepository;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.repository.UserKeywordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {
    private final QuestionsRepository questionRepository;
    private final UserKeywordsRepository userKeywordsRepository;

    @Autowired
    public SurveyService(QuestionsRepository questionRepository, UserKeywordsRepository userKeywordsRepository) {
        this.questionRepository = questionRepository;
        this.userKeywordsRepository = userKeywordsRepository;
    }

    public List<Questions> getAllQuestions() {
        return questionRepository.findAll();
    }

    public String keywordsRegister(UserKeywordsDTO dto) {
        UserKeywords userKeywords = UserKeywords.builder()
                .userId(dto.getUserId())
                .optionId(dto.getOptionId())
                .build();
        userKeywordsRepository.save(userKeywords);
        return userKeywords.getUserId();
    }


}