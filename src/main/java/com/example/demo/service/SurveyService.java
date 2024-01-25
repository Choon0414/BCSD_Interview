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
    private AnimalKeywordsRepository animalKeywordsRepository;

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

    public void makeAnimalDTO(List<Animal> animalList, UserKeywordsDTO userKeywordsDTO){
        int optionId = userKeywordsDTO.getOptionId();
        Optional<Animal> optionalAnimal = animalKeywordsRepository.findByAnimalId(optionId);
        if(optionalAnimal.isPresent()){
            Animal animal = optionalAnimal.get();
            if(!animalList.contains(animal)) animalList.add(animal);
        }
    }
    public void makeOptionList(List<Options> optionList){

    }
    public String matching(List<Animal> animalList, List<Options> optionList){

        return null;
    }
}