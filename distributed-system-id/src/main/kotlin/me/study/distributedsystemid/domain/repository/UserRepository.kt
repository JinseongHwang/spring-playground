package me.study.distributedsystemid.domain.repository

import me.study.distributedsystemid.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>
