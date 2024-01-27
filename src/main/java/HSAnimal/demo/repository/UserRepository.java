package HSAnimal.demo.repository;

import HSAnimal.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId); // 사용자를 user_id(String)으로 찾기 위한 메소드
}
