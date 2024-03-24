package me.study.artillery

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @GetMapping("/hello")
    fun sayHello(): String {
        return "Hello, World!"
    }
}