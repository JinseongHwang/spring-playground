package me.study.testcodewitharchitecture.user.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import me.study.testcodewitharchitecture.common.domain.exception.ResourceNotFoundException;
import me.study.testcodewitharchitecture.common.service.port.ClockHolder;
import me.study.testcodewitharchitecture.common.service.port.UuidHolder;
import me.study.testcodewitharchitecture.user.domain.User;
import me.study.testcodewitharchitecture.user.domain.UserCreate;
import me.study.testcodewitharchitecture.user.domain.UserStatus;
import me.study.testcodewitharchitecture.user.domain.UserUpdate;
import me.study.testcodewitharchitecture.user.service.port.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Builder
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CertificationService certificationService;
    private final UuidHolder uuidHolder;
    private final ClockHolder clockHolder;

    public User getByEmail(String email) {
        return userRepository.findByEmailAndStatus(email, UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Users", email));
    }

    public User getById(long id) {
        return userRepository.findByIdAndStatus(id, UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Users", id));
    }

    @Transactional
    public User create(UserCreate userCreate) {
        User user = User.from(userCreate, uuidHolder);
        user = userRepository.save(user);
        certificationService.send(user.getEmail(), user.getId(), user.getCertificationCode());
        return user;
    }

    @Transactional
    public User update(long id, UserUpdate userUpdate) {
        User user = getById(id);
        user = user.update(userUpdate);
        user = userRepository.save(user);
        return user;
    }

    @Transactional
    public void login(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", id));
        user = user.login(clockHolder);
        userRepository.save(user);
    }

    @Transactional
    public void verifyEmail(long id, String certificationCode) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", id));
        user = user.certificate(certificationCode);
        userRepository.save(user);
    }


}
