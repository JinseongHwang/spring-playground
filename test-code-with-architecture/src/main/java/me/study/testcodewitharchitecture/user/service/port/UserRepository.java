package me.study.testcodewitharchitecture.user.service.port;

import me.study.testcodewitharchitecture.user.domain.UserStatus;
import me.study.testcodewitharchitecture.user.infrastructure.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findById(long id);

    Optional<UserEntity> findByIdAndStatus(long id, UserStatus userStatus);

    Optional<UserEntity> findByEmailAndStatus(String email, UserStatus userStatus);

    UserEntity save(UserEntity userEntity);

}
