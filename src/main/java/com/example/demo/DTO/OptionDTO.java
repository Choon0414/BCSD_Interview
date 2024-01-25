package com.example.demo.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OptionDTO {
    private int optionId;
    private String content;
}
