package com.example.demo.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
    private String userId;
    private String username;
    private String password;
    private String email;
}
