package me.jinseonghwang.restfuljwtboard.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jinseonghwang.restfuljwtboard.dto.UserDto;
import me.jinseonghwang.restfuljwtboard.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto insertUser(UserDto user) {
        return userRepository.insertUser(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserDto getUserByUserId(String userId) {
        return userRepository.getUserByUserId(userId);
    }

    public void updateUserPw(String userId, UserDto user) {
        userRepository.updateUserPw(userId, user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteUser(userId);
    }
}
