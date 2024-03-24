package me.study.artillery

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScenarioController {

    @PostMapping("/login")
    fun login(): String {
        sleep(20)
        return "Login Success"
    }

    @GetMapping("/some-function-one")
    fun someFunctionOne(): String {
        sleep(30)
        return "Result One"
    }

    @GetMapping("/some-function-two")
    fun someFunctionTwo(): String {
        sleep(15)
        return "Result Two"
    }

    private fun sleep(millisecond: Long) {
        try {
            Thread.sleep(millisecond)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }
}