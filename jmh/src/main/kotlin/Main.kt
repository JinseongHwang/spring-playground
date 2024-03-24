import com.sun.management.HotSpotDiagnosticMXBean
import java.lang.management.ManagementFactory


@JvmInline
value class LengthValue(
    val meters: Double
) {
    fun toCm(): Double = meters * 100
    fun toKm(): Double = meters / 1000

    companion object {
        fun fromCm(cm: Double): LengthValue = LengthValue(cm / 100)
        fun fromKm(km: Double): LengthValue = LengthValue(km * 1000)
    }
}

class LengthRegular(
    val meters: Double
) {
    fun toCm(): Double = meters * 100
    fun toKm(): Double = meters / 1000

    companion object {
        fun fromCm(cm: Double): LengthRegular = LengthRegular(cm / 100)
        fun fromKm(km: Double): LengthRegular = LengthRegular(km * 1000)
    }
}

fun main() {
    testMemoryValueClassArray()
    println("Finish...")
}

fun testMemoryRegularClassList() {
    val instances = mutableListOf<LengthRegular>()
    (1..10_000_000).forEach {
        instances.add(LengthRegular(it + 0.0))
    }
    createHeapDump("RegularClass in List")
}

fun testMemoryValueClassList() {
    val instances = mutableListOf<LengthValue>()
    (1..10_000_000).forEach {
        instances.add(LengthValue(it + 0.0))
    }
    createHeapDump("ValueClass in List")
}

/**
 * Total Bytes: 11,357,120
 * Total Classes: 1,694
 * Total Instances: 302,245
 * Classloaders: 8
 * GC Roots: 1,589
 * 201,227, 4.83mb
 */
fun testMemoryRegularClassArray() {
    (0..10_000_000).forEach {
        LengthRegular(it + 0.0)
    }
    createHeapDump("RegularClass")
}

/**
 * Total Bytes: 6,640,576
 * Total Classes: 1,694
 * Total Instances: 101,009
 * Classloaders: 8
 * GC Roots: 1,589
 */
fun testMemoryValueClassArray() {
    (0..10_000_000).forEach {
        LengthValue(it + 0.0)
    }
    createHeapDump("ValueClass")
}

fun createHeapDump(key: String = "") {
    val path = "/Users/hy3d670wn2/IdeaProjects/spring-playground/jmh"
    val liveOnly = false
    val server = ManagementFactory.getPlatformMBeanServer()
    try {
        val mxBean = ManagementFactory.newPlatformMXBeanProxy(
            server, "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean::class.java
        )
        mxBean.dumpHeap("$path/heapdump-$key-${System.currentTimeMillis()}.hprof", liveOnly)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun addLengths(length1: LengthValue, length2: LengthValue): LengthValue {
    return LengthValue(length1.meters + length2.meters)
}
