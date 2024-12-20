package com.jjlee.lockmode

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class LockmodeApplication

fun main(args: Array<String>) {
	runApplication<LockmodeApplication>(*args)
}
