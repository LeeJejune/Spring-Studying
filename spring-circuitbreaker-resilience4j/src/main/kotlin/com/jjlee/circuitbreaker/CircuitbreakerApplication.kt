package com.jjlee.circuitbreaker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CircuitbreakerApplication

fun main(args: Array<String>) {
	runApplication<CircuitbreakerApplication>(*args)
}
