package me.study.testcodewitharchitecture.user.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.study.testcodewitharchitecture.user.domain.User;
import me.study.testcodewitharchitecture.user.domain.UserStatus;

@Getter
@Builder
public class MyProfileResponse {

    private final Long id;
    private final String email;
    private final String nickname;
    private final String address;
    private final UserStatus status;
    private final Long lastLoginAt;

    public static MyProfileResponse from(User user) {
        return MyProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .status(user.getStatus())
                .address(user.getAddress())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }
}
