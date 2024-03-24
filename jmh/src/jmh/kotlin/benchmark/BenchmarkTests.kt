package benchmark

import RegularClass
import ValueClass
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(2)
open class BenchmarkTests {

    @Benchmark
    fun testSumValueClass() {
        var sum = 0L
        (1..10_000_000).forEach {
            val obj = ValueClass(it)
            sum += obj.number
        }
    }

    @Benchmark
    fun testSumRegularClass() {
        var sum = 0L
        (1..10_000_000).forEach {
            val obj = RegularClass(it)
            sum += obj.number
        }
    }
}
