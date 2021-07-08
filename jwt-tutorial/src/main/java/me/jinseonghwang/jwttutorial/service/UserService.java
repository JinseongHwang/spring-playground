package me.jinseonghwang.jwttutorial.service;

import me.jinseonghwang.jwttutorial.dto.UserDto;
import me.jinseonghwang.jwttutorial.entity.Authority;
import me.jinseonghwang.jwttutorial.entity.User;
import me.jinseonghwang.jwttutorial.repository.UserRepository;
import me.jinseonghwang.jwttutorial.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입
     */
    @Transactional
    public User signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
            .authorityName("ROLE_USER")
            .build();

        User user = User.builder()
            .username(userDto.getUsername())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .nickname(userDto.getNickname())
            .authorities(Collections.singleton(authority))
            .activated(true)
            .build();

        return userRepository.save(user);
    }

    // username 을 전달받아 해당 유저의 정보 및 권한 정보를 반환한다.
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    // SecurityUtil 의 getCurrentUsername() 메서드가 반환하는 유저의 정보 및 권한 정보를 반환한다.
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}