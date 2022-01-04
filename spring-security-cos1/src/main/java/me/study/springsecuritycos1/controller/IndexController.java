package me.study.springsecuritycos1.controller;

import lombok.RequiredArgsConstructor;
import me.study.springsecuritycos1.model.User;
import me.study.springsecuritycos1.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @ResponseBody
    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/loginForm") // 스프링 시큐리티가 가로챔 - SecurityConfig 생성 이후 무효화
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(User user) {
        user.setRole("ROLE_USER");

        final String rawPassword = user.getPassword();
        final String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);

        final User savedUser = userRepository.save(user);
        System.out.println("savedUser = " + savedUser);

        return "redirect:/loginForm";
    }
}
