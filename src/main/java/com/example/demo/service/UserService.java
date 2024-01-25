package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 회원가입
    public String signup(UserDTO dto) {
        User user = User.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
        return userRepository.save(user).getUserId();
    }
}
