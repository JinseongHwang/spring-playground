package me.study.artillery

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ParameterController {

    private val logger = LoggerFactory.getLogger(ParameterController::class.java)

    @PostMapping("/login-with-id-password")
    fun loginWithIdPassword(
        @RequestBody idAndPassword: IdAndPassword
    ): String {
        logger.info("{} / {}", idAndPassword.id, idAndPassword.password)
        return "Login Success"
    }

    @GetMapping("/search")
    fun search(
        @RequestParam query: String
    ): String {
        logger.info("{}", query)
        return "Query Success"
    }

    class IdAndPassword(
        val id: String,
        val password: String
    )
}