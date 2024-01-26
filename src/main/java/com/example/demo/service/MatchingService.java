package com.example.demo.service;

import com.example.demo.DTO.AnimalDTO;
import com.example.demo.DTO.OptionDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchingService {

    private final OptionsRepository optionsRepository;
    private final AnimalRepository animalRepository;
    private final UserKeywordsRepository userKeywordsRepository;
    private final AnimalKeywordsRepository animalKeywordsRepository;

    @Autowired
    public MatchingService (OptionsRepository optionsRepository, AnimalRepository animalRepository,
                            UserKeywordsRepository userKeywordsRepository, AnimalKeywordsRepository animalKeywordsRepository){
        this.optionsRepository = optionsRepository;
        this.animalRepository = animalRepository;
        this.userKeywordsRepository = userKeywordsRepository;
        this.animalKeywordsRepository = animalKeywordsRepository;
    }

    // 사용자의 키워드 리스트 생성
    public List<OptionDTO> getOptionsDTOList(String userId){
        List<UserKeywords> optionsList = userKeywordsRepository.findAllByUserId(userId);
        List<OptionDTO> optionsDTOList = new ArrayList<>();
        for (UserKeywords option: optionsList) {
            int optionId = option.getOptionId();
            if (optionsRepository.findByOptionId(optionId).isPresent()) {
                String contents = optionsRepository.findByOptionId(optionId).get().getContent();
                OptionDTO optionsDTO = OptionDTO.builder()
                        .optionId(optionId)
                        .content(contents)
                        .build();
                optionsDTOList.add(optionsDTO);
            }
        }
        return optionsDTOList;
    }

    // 사용자와 겹치는 동물 리스트 생성
    public Set<AnimalDTO> getAnimalDTOList(List<OptionDTO> optionDTOList){
        Set<AnimalDTO> animalDTOList = new HashSet<>();

        for(OptionDTO option : optionDTOList){
            List<AnimalKeywords> animalKeywordsList = animalKeywordsRepository.findAllByOptionId(option.getOptionId());
            for (AnimalKeywords animalKeywords : animalKeywordsList) {
                Optional<Animal> optionalAnimal = animalRepository.findByAnimalId(animalKeywords.getAnimalId());
                if (optionalAnimal.isPresent()) {
                    animalDTOList.add(AnimalDTO.builder()
                            .animalId(optionalAnimal.get().getAnimalId())
                            .animalName(optionalAnimal.get().getAnimalName())
                            .build());
                }
            }
        }
        return animalDTOList;
    }


    // 매칭된 동물들 반환
    public String sumWeights(Set<AnimalDTO> animalDTOSet, List<OptionDTO> optionDTOList){
        return null;
    }
}
