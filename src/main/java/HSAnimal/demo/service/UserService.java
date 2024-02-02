package HSAnimal.demo.service;

import HSAnimal.demo.DTO.*;
import HSAnimal.demo.configuration.JwtProperties;
import HSAnimal.demo.configuration.TokenProvider;
import HSAnimal.demo.domain.RefreshToken;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.domain.UserKeywords;
import HSAnimal.demo.repository.RefreshTokenRepository;
import HSAnimal.demo.repository.UserKeywordsRepository;
import HSAnimal.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;
    private final UserKeywordsRepository userKeywordsRepository;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                        TokenProvider tokenProvider, RefreshTokenRepository refreshTokenRepository,
                       JwtProperties jwtProperties, UserKeywordsRepository userKeywordsRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenProvider = tokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtProperties = jwtProperties;
        this.userKeywordsRepository = userKeywordsRepository;
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
    public CreateAccessTokenResponseDTO authenticateUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByUserId(userDTO.getUserId());
        if (userOptional.isPresent() && bCryptPasswordEncoder.matches(userDTO.getPassword(),
                userOptional.get().getPassword())) {
            CreateAccessTokenResponseDTO tokenDTO = new CreateAccessTokenResponseDTO(tokenProvider
                    .generateToken(userOptional.get(), Duration.ofMinutes(jwtProperties.getExpirationMinutes())));
            String refreshToken = tokenProvider.generateRefreshToken(
                    Duration.ofHours(jwtProperties.getRefreshExpirationHours()));	// 리프레시 토큰 생성
            refreshTokenRepository.findByUserId(userDTO.getUserId())
                    .ifPresentOrElse(
                            it -> it.updateRefreshToken(refreshToken),
                            () -> refreshTokenRepository.save(new RefreshToken(userDTO.getUserId(), refreshToken))
                    );
            return tokenDTO;
        } else {
            throw new IllegalArgumentException("로그인 오류");
        }
    }

    public void updateUser(String userId, UpdateUserDTO updateUserDTO){
        userRepository.findByUserId(userId)
                .map(user -> {
                    user.changeName(updateUserDTO.getUsername());
                    user.changeEmail(updateUserDTO.getEmail());
                    user.changePassword(bCryptPasswordEncoder.encode(updateUserDTO.getPassword()));
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }

    public void deleteUser(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        userRepository.delete(user);
    }

    public void deleteUserKeywords(String userId, List<UserKeywordsDTO> userKeywordsDTOList) {
        for (UserKeywordsDTO userKeywordsDTO: userKeywordsDTOList) {
            int optionId = userKeywordsDTO.getOptionId();
            UserKeywords userKeywords = userKeywordsRepository.findByUserIdAndOptionId(userId, optionId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
            userKeywordsRepository.delete(userKeywords);
        }
    }
}
