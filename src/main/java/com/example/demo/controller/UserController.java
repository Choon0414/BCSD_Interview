package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/{user_id}")
    public User read(@PathVariable String user_id) {
        return userRepository.findByUserId(user_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO request) {
        String userId = userService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User with ID " + userId + " registered successfully.");
    }

    @PutMapping("/{user_id}")
    public User update(@PathVariable String user_id, @RequestBody User updatedUser) {
        return userRepository.findByUserId(user_id)
                .map(user -> {
                    // 업데이트할 필드 설정
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    // 필요한 다른 필드 업데이트

                    // 저장하고 업데이트된 사용자 반환
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // Delete: 사용자 삭제
    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> delete(@PathVariable String user_id) {
        return userRepository.findByUserId(user_id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
