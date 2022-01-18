package me.lab.thymeleaf.controller;

import me.lab.thymeleaf.dto.UserDto;
import me.lab.thymeleaf.dto.UserDto.ADDRESS;
import me.lab.thymeleaf.dto.UserDto.READ;
import me.lab.thymeleaf.dto.UserDto.READ.READBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "jinseong");
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        final READ res = READ.builder()
            .name("황진성")
            .email("eddy5360@naver.com")
            .address(ADDRESS.builder()
                .addr1("부산광역시")
                .addr2("광안리")
                .build()
            )
            .build();
        model.addAttribute("res", res);
        return "info";
    }
}
