package me.study.testcontainers.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                             .orElseThrow(RuntimeException::new);
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public long getUserCount() {
        return userRepository.count();
    }
}
