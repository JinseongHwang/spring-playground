package me.study.testcodewitharchitecture.user.controller;

import me.study.testcodewitharchitecture.user.domain.UserCreate;
import me.study.testcodewitharchitecture.user.controller.response.UserResponse;
import me.study.testcodewitharchitecture.user.repository.UserEntity;
import me.study.testcodewitharchitecture.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저(users)")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserCreateController {

    private final UserController userController;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreate userCreate) {
        UserEntity userEntity = userService.create(userCreate);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userController.toResponse(userEntity));
    }

}
