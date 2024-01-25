package com.example.demo.service;

import com.example.demo.DTO.AnimalDTO;
import com.example.demo.DTO.OptionDTO;
import com.example.demo.DTO.UserKeywordsDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MatchingService {
    private final AnimalKeywordsRepository animalKeywordsRepository;
    private final QuestionsRepository questionsRepository;
    private final OptionsRepository optionsRepository;
    private final UserKeywordsRepository userKeywordsRepository;
    private final AnimalRepository animalRepository;

    // 사용자와 겹치는 동물 리스트 생성
    public List<AnimalDTO> getAnimalDTOLIst(List<OptionDTO> optionDTOList){
        List<AnimalDTO> animalDTOList = new ArrayList<>();
        for(OptionDTO dto: optionDTOList){
            int optionId = dto.getOptionId();
            List<AnimalKeywords> animalKeywordsList = animalKeywordsRepository.findByAnimalId(optionId);
            for (AnimalKeywords animalKeywords : animalKeywordsList) {
                Optional<Animal> optionalAnimal = animalRepository.findByAnimalId(animalKeywords.getAnimalId());
                if (optionalAnimal.isPresent()) {
                    AnimalDTO animalDTO = AnimalDTO.builder()
                            .animalId(optionalAnimal.get().getAnimalId())
                            .animalName(optionalAnimal.get().getAnimalName())
                            .build();
                    if (!animalDTOList.contains(animalDTO)) {
                        animalDTOList.add(animalDTO);
                    }
                }
            }
        }
        return animalDTOList;
    }

    // 사용자의 키워드 리스트 생성
    public List<OptionDTO> getOptionsDTOList(String userId){
        List<UserKeywords> optionsList = userKeywordsRepository.findAllByUserId(userId);
        List<OptionDTO> optionsDTOList = new ArrayList<>();
        for (UserKeywords option: optionsList) {
            int optionId = option.getOptionId();
            OptionDTO optionsDTO = OptionDTO.builder()
                    .optionId(optionId)
                    .content(optionsRepository.findByOptionId(optionId).get().getContent())
                    .build();
            optionsDTOList.add(optionsDTO);
        }
        return optionsDTOList;
    }

    // 매칭된 동물들 반환
    public String matching(List<AnimalDTO> animalDTOList, List<OptionDTO> optionDTOList){
        return null;
    }
}
