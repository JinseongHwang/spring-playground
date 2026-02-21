package me.study.observability

import org.springframework.boot.availability.ApplicationAvailability
import org.springframework.boot.availability.AvailabilityChangeEvent
import org.springframework.boot.availability.LivenessState
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class ApplicationAvailabilityService(
    private val eventPublisher: ApplicationEventPublisher,
    private val availability: ApplicationAvailability
) {

    fun checkStatus() {
        val currentLiveness = availability.livenessState
        val currentReadiness = availability.readinessState

        println("Liveness: $currentLiveness")
        println("Readiness: $currentReadiness")
    }

    fun handleDeadlock() {
        val event = AvailabilityChangeEvent("데드락 감지!", LivenessState.BROKEN)
        eventPublisher.publishEvent(

        )
    }
}