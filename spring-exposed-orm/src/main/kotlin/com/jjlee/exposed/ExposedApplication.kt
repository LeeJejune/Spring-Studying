package com.jjlee.exposed

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExposedApplication

fun main(args: Array<String>) {
	runApplication<ExposedApplication>(*args)
}
