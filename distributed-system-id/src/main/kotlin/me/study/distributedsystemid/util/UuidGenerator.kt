package me.study.distributedsystemid.util

import org.springframework.stereotype.Component
import java.util.*

@Component
class UuidGenerator {

    @Synchronized
    fun nextId() = UUID.randomUUID().toString()
}
