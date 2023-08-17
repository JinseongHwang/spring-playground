package me.study.distributedsystemid.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table

@Table(name = "post")
@Entity
class Post(
    @Id
    var id: Long,

    @Lob
    var contents: String,
)

data class PostDto(
    val contents: String,
)