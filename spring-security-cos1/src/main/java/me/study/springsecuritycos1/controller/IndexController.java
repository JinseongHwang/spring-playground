package me.study.springsecuritycos1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

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

    @ResponseBody
    @GetMapping("/login") // 스프링 시큐리티가 가로챔
    public String login() {
        return "login";
    }

    @ResponseBody
    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @ResponseBody
    @GetMapping("/joinProc")
    public String joinProc() {
        return "회원가입 완료됨!";
    }
}
