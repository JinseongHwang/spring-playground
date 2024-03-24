package me.study.artillery

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ThreadLocalRandom

@RestController
class HighLoadController {

    @GetMapping("/high-load-cpu")
    fun highLoadCpu(): Int {
        var sum = 0

        // 랜덤한 수를 뽑아서 더하는 연산
        for (i in 0..<20_000_000) {
            val randomInt = ThreadLocalRandom.current().nextInt()
            sum += randomInt
        }

        return sum
    }

    @GetMapping("/high-load-memory")
    fun highLoadMemory(): Int {
        val memoryIntensiveList = mutableListOf<Int>()

        for (i in 0..<500_000) {
            // int를 추가하는 과정에서 새로운 Integer 래퍼 클래스 인스턴스 생성
            memoryIntensiveList.add(i)
        }

        return memoryIntensiveList.size
    }
}