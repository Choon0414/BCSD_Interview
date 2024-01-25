package com.example.demo.service;

import com.example.demo.DTO.AnimalDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MatchingService {
/*
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    public List<AnimalDTO> calculateMatchingAnimals(String userId) {
        User user = userRepository.findByUserId(userId);
        List<UserKeywords> userKeywords = user.getUserKeywords();

        Map<Animal, Integer> animalScores = new HashMap<>();

        for (UserKeywords uk : userKeywords) {
            Options option = optionRepository.findById(uk.getOptionId()).orElse(null);
            if (option != null) {
                Questions question = questionRepository.findById(option.getQuestionId()).orElse(null);
                if (question != null) {
                    int weight = question.getWeight();

                    List<AnimalKeywords> animalKeywords = AnimalKeywordRepository.findByOptionId(uk.getOptionId());
                    for (AnimalKeywords ak : animalKeywords) {
                        Animal animal = animalRepository.findById(ak.getAnimalId()).orElse(null);
                        if (animal != null) {
                            animalScores.put(animal, animalScores.getOrDefault(animal, 0) + weight);
                        }
                    }
                }
            }
        }

        // 가중치 합계가 큰 순으로 정렬
        return animalScores.entrySet().stream()
                .sorted(Map.Entry.<Animal, Integer>comparingByValue().reversed())
                .map(e -> new AnimalDTO(e.getKey().getAnimalId(), e.getKey().getAnimalName(), e.getValue()))
                .collect(Collectors.toList());
    }
 */


}
