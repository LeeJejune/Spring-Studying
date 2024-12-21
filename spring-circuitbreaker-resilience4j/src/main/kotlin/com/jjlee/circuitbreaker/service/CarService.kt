package com.jjlee.circuitbreaker.service

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.stereotype.Service

@Service
class CarService(
    private val circuitBreakerRegistry: CircuitBreakerRegistry
) {

    @CircuitBreaker(name = "car-info-circuit-breaker", fallbackMethod = "fallbackGetCarInfo")
    fun getCarInfo(id: String): String {
        // Redis에서 조회한다고 가정
        return when (id) {
            "ABC" -> "Redis에서 가져온 $id 차량 정보"
            "XYZ" -> "Redis에서 가져온 $id 차량 정보"
            else -> throw RuntimeException("Redis에서 $id 차량 정보를 찾을 수 없습니다")
        }
    }

    fun fallbackGetCarInfo(id: String, t: Throwable): String {
        // RDB에서 조회한다고 가정
        return "RDB에서 가져온 $id 차량의 대체 정보"
    }

    fun monitorAndControl() {
        val circuitBreaker = circuitBreakerRegistry.circuitBreaker("car-info-circuit-breaker")
        if (circuitBreaker.state.toString() == "OPEN" && checkRecoveryCondition()) {
            circuitBreaker.transitionToHalfOpenState()
        }
    }

    fun checkRecoveryCondition() : Boolean {
        // 레디스가 복구 되었다고 가정
        return true
    }
}