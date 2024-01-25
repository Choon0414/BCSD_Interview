package com.example.demo.service;

import com.example.demo.DTO.AnimalDTO;
import com.example.demo.DTO.UserKeywordsDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchingService {
    private AnimalKeywordsRepository animalKeywordsRepository;

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
