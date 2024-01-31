package HSAnimal.demo.service;

import HSAnimal.demo.DTO.LoginResponseDTO;
import HSAnimal.demo.DTO.UserDTO;
import HSAnimal.demo.configuration.JwtProperties;
import HSAnimal.demo.configuration.TokenProvider;
import HSAnimal.demo.domain.RefreshToken;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.repository.RefreshTokenRepository;
import HSAnimal.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                        TokenProvider tokenProvider, RefreshTokenRepository refreshTokenRepository,
                       JwtProperties jwtProperties){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenProvider = tokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtProperties = jwtProperties;
    }

    // 회원가입
    public String signup(UserDTO dto) {
        User user = User.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .build();
        return userRepository.save(user).getUserId();
    }

    // 사용자 인증
    public String authenticateUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByUserId(userDTO.getUserId());
        if (userOptional.isPresent() && bCryptPasswordEncoder.matches(userDTO.getPassword(),
                userOptional.get().getPassword())) {
            LoginResponseDTO loginDTO = new LoginResponseDTO(tokenProvider
                    .generateToken(userOptional.get(), Duration.ofMinutes(jwtProperties.getExpirationMinutes())));
            String refreshToken = tokenProvider.generateRefreshToken(
                    Duration.ofHours(jwtProperties.getRefreshExpirationHours()));	// 리프레시 토큰 생성
            // 리프레시 토큰이 이미 있으면 토큰을 갱신하고 없으면 토큰을 추가
            refreshTokenRepository.findByUserId(userDTO.getUserId())
                    .ifPresentOrElse(
                            it -> it.update(refreshToken),
                            () -> {
                                RefreshToken newRefreshToken = new RefreshToken(userDTO.getUserId(), refreshToken);
                                refreshTokenRepository.save(newRefreshToken);
                            }
                    );
            return loginDTO.getAccessToken();
        }
        return "로그인 오류";
    }



    public User findById(String userId){
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}