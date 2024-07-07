package me.study.testcodewitharchitecture.model.dto;

import me.study.testcodewitharchitecture.model.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String email;
    private String nickname;
    private UserStatus status;
    private Long lastLoginAt;
}
