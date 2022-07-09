package me.study.resttemplate.controller;

import lombok.RequiredArgsConstructor;
import me.study.resttemplate.domain.user.User;
import me.study.resttemplate.domain.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable int id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }
}
