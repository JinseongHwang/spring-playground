package me.study.distributedsystemid.service

import me.study.distributedsystemid.domain.entity.Post
import me.study.distributedsystemid.domain.entity.PostDto
import me.study.distributedsystemid.domain.entity.User
import me.study.distributedsystemid.domain.entity.UserDto
import me.study.distributedsystemid.domain.repository.PostRepository
import me.study.distributedsystemid.domain.repository.UserRepository
import me.study.distributedsystemid.util.SnowflakeIdGenerator
import me.study.distributedsystemid.util.UuidGenerator
import org.springframework.stereotype.Service

@Service
class BlogService(
    // repositories
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,

    // id generators
    private val snowflakeIdGenerator: SnowflakeIdGenerator,
    private val uuidGenerator: UuidGenerator
) {

    fun saveUser(dto: UserDto) {
        val user = User(
            id = uuidGenerator.nextId(),
            name = dto.name,
            age = dto.age
        )
        userRepository.save(user)
    }

    fun savePost(dto: PostDto) {
        val post = Post(
            id = snowflakeIdGenerator.nextId(),
            contents = dto.contents
        )
        postRepository.save(post)
    }
}
