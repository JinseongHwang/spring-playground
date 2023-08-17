package me.study.distributedsystemid.domain.repository

import me.study.distributedsystemid.domain.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>
