package me.study.artillery

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.ConcurrentHashMap


@RestController
class HashController {
    private val cashHashResult = ConcurrentHashMap<String, String>()

    @GetMapping("/no-cache-hash-string")
    fun noCacheHashString(@RequestParam input: String): String {
        return calculateHash(input)
    }

    @GetMapping("/cached-hash-string")
    fun cachedHashString(@RequestParam input: String): String? {
        if (cashHashResult.containsKey(input)) {
            return cashHashResult[input]
        }

        val hashedResult = calculateHash(input)
        cashHashResult[input] = hashedResult
        return hashedResult
    }

    private fun calculateHash(input: String): String {
        var input = input
        try {
            val md = MessageDigest.getInstance("SHA-256")

            for (i in 0..<50_000) {
                val bytes = input.toByteArray()
                val hashedBytes = md.digest(bytes)
                input = bytesToHex(hashedBytes)
                md.reset()
            }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return input
    }

    private fun bytesToHex(bytes: ByteArray): String {
        val result = StringBuilder()
        for (aByte in bytes) {
            result.append(((aByte.toInt() and 0xff) + 0x100).toString(16).substring(1))
        }
        return result.toString()
    }
}