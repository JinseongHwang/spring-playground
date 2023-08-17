package me.study.distributedsystemid.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "user")
@Entity
class User(
    @Id
    var id: String,

    var name: String,

    var age: Int
)

data class UserDto(
    val name: String,
    val age: Int
)
