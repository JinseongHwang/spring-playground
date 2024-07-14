package me.study.testcodewitharchitecture.user.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.study.testcodewitharchitecture.user.controller.response.MyProfileResponse;
import me.study.testcodewitharchitecture.user.controller.response.UserResponse;
import me.study.testcodewitharchitecture.user.domain.User;
import me.study.testcodewitharchitecture.user.domain.UserUpdate;
import me.study.testcodewitharchitecture.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "유저(users)")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(toResponse(userService.getById(id)));
    }

    @GetMapping("/{id}/verify")
    public ResponseEntity<Void> verifyEmail(
            @PathVariable long id,
            @RequestParam String certificationCode) {
        userService.verifyEmail(id, certificationCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("http://localhost:3000"))
                .build();
    }

    @GetMapping("/me")
    public ResponseEntity<MyProfileResponse> getMyInfo(
            @Parameter(name = "EMAIL", in = ParameterIn.HEADER)
            @RequestHeader("EMAIL") String email // 일반적으로 스프링 시큐리티를 사용한다면 UserPrincipal 에서 가져옵니다.
    ) {
        User user = userService.getByEmail(email);
        userService.login(user.getId());
        return ResponseEntity
                .ok()
                .body(toMyProfileResponse(user));
    }

    @PutMapping("/me")
    @Parameter(in = ParameterIn.HEADER, name = "EMAIL")
    public ResponseEntity<MyProfileResponse> updateMyInfo(
            @Parameter(name = "EMAIL", in = ParameterIn.HEADER)
            @RequestHeader("EMAIL") String email, // 일반적으로 스프링 시큐리티를 사용한다면 UserPrincipal 에서 가져옵니다.
            @RequestBody UserUpdate userUpdate
    ) {
        User user = userService.getByEmail(email);
        user = userService.update(user.getId(), userUpdate);
        return ResponseEntity
                .ok()
                .body(toMyProfileResponse(user));
    }

    public UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setNickname(user.getNickname());
        userResponse.setStatus(user.getStatus());
        userResponse.setLastLoginAt(user.getLastLoginAt());
        return userResponse;
    }

    public MyProfileResponse toMyProfileResponse(User user) {
        MyProfileResponse myProfileResponse = new MyProfileResponse();
        myProfileResponse.setId(user.getId());
        myProfileResponse.setEmail(user.getEmail());
        myProfileResponse.setNickname(user.getNickname());
        myProfileResponse.setStatus(user.getStatus());
        myProfileResponse.setAddress(user.getAddress());
        myProfileResponse.setLastLoginAt(user.getLastLoginAt());
        return myProfileResponse;
    }
}
