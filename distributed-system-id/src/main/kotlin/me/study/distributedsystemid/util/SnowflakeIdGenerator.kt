package me.study.distributedsystemid.util

import org.springframework.stereotype.Component
import java.net.NetworkInterface
import java.security.SecureRandom
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Component
class SnowflakeIdGenerator {

    companion object {
        private val UNUSED_BITS = 1 // Sign bit, Unused (always set to 0)
        private val EPOCH_BITS = 41
        private val NODE_ID_BITS = 10
        private val SEQUENCE_BITS = 12

        private val maxNodeId = (1L shl NODE_ID_BITS) - 1
        private val maxSequence = (1L shl SEQUENCE_BITS) - 1

        private val DEFAULT_CUSTOM_EPOCH = defaultEpochMilli()

        private fun defaultEpochMilli(): Long {
            val initDateTime = LocalDateTime.of(2023, 8, 18, 0, 0, 0)
            return initDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        }
    }

    private var nodeId: Long = 0
    private var customEpoch: Long = 0

    @Volatile
    private var lastTimestamp = -1L

    @Volatile
    private var sequence = 0L

    constructor(nodeId: Long, customEpoch: Long) {
        require(!(nodeId < 0 || nodeId > maxNodeId)) {
            String.format("NodeId must be between %d and %d", 0, maxNodeId)
        }
        this.nodeId = nodeId
        this.customEpoch = customEpoch
    }

    constructor(nodeId: Long) : this(nodeId, DEFAULT_CUSTOM_EPOCH)

    constructor() {
        this.nodeId = createNodeId()
        this.customEpoch = DEFAULT_CUSTOM_EPOCH
    }

    @Synchronized
    fun nextId(): Long {
        var currentTimestamp = timestamp()

        check(currentTimestamp >= lastTimestamp) { "Invalid System Clock!" }

        if (currentTimestamp == lastTimestamp) {
            sequence = sequence + 1 and maxSequence
            if (sequence == 0L) {
                currentTimestamp = waitNextMillis(currentTimestamp)
            }
        } else {
            sequence = 0
        }
        lastTimestamp = currentTimestamp

        return (currentTimestamp shl NODE_ID_BITS + SEQUENCE_BITS or (nodeId shl SEQUENCE_BITS) or sequence)
    }

    private fun timestamp(): Long {
        return Instant.now().toEpochMilli() - customEpoch
    }

    private fun waitNextMillis(timestamp: Long): Long {
        var currentTimestamp = timestamp
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp()
        }
        return currentTimestamp
    }

    private fun createNodeId(): Long {
        var nodeId: Long
        nodeId = try {
            val sb = StringBuilder()
            val networkInterfaces = NetworkInterface.getNetworkInterfaces()
            while (networkInterfaces.hasMoreElements()) {
                val networkInterface = networkInterfaces.nextElement()
                val mac = networkInterface.getHardwareAddress()
                if (mac != null) {
                    for (macPort in mac) {
                        sb.append(String.format("%02X", macPort))
                    }
                }
            }
            sb.toString().hashCode().toLong()
        } catch (ex: Exception) {
            SecureRandom().nextInt()
        }.toLong()
        nodeId = nodeId and maxNodeId
        return nodeId
    }

    fun parse(id: Long): LongArray {
        val maskNodeId = (1L shl NODE_ID_BITS) - 1 shl SEQUENCE_BITS
        val maskSequence = (1L shl SEQUENCE_BITS) - 1
        val timestamp = (id shr NODE_ID_BITS + SEQUENCE_BITS) + customEpoch
        val nodeId = id and maskNodeId shr SEQUENCE_BITS
        val sequence = id and maskSequence
        return longArrayOf(timestamp, nodeId, sequence)
    }
}
