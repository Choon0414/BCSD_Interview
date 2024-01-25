package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder        // SQL 파라미터에 값을 쉽게 넣어주기 위함
@Entity(name = "users")  // 해당 class에서 사용할 테이블 명
@AllArgsConstructor // 매개변수에 대한 생성자들을 자동 생성
@NoArgsConstructor  // 기본생성자를 자동 생성
@Getter             // get 메소드를 자동 생성
@Setter             // set 메소드를 자동 생성
@ToString           // toString 메소드 자동 생성
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "user_id", unique = true, nullable = false, length = 100)
    private String userId; //유저id

    @Column(name = "username", nullable = false, length = 255)
    private String username; //유저이름

    @Column(name = "password", nullable = false)
    private String password; //비밀번호

    @Column(name = "email", unique = true , nullable = false)
    private String email; //비밀번호
}

