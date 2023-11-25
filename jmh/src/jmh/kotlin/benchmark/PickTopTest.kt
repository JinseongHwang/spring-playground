package benchmark

import org.openjdk.jmh.annotations.*
import java.util.PriorityQueue
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(2)
@Measurement(iterations = 5, time = 1)
@Warmup(iterations = 2, time = 1)
open class PickTopTest {

    @Benchmark
    fun useSortedList(): Long {
        val ls = mutableListOf<Long>()
        (1..1_000_000L).forEach {
            ls.add(it)
        }
        ls.sortDescending()
        return ls[0]
    }

    @Benchmark
    fun usePriorityQueue(): Long {
        val pq = PriorityQueue<Long>()
        (1..1_000_000L).forEach {
            pq.add(it)
        }
        return pq.peek()
    }
}
