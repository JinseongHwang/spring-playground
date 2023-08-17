package me.study.distributedsystemid.controller

import me.study.distributedsystemid.domain.entity.PostDto
import me.study.distributedsystemid.domain.entity.UserDto
import me.study.distributedsystemid.service.BlogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class BlogController(
    private val blogService: BlogService
) {

    @PostMapping("/user")
    fun saveUser(
        @RequestBody userDto: UserDto
    ): ResponseEntity<Unit> {
        blogService.saveUser(userDto)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PostMapping("/post")
    fun savePost(
        @RequestBody postDto: PostDto
    ): ResponseEntity<Unit> {
        blogService.savePost(postDto)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
