package com.jjlee.kafkaa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaaApplication

fun main(args: Array<String>) {
	runApplication<KafkaaApplication>(*args)
}
