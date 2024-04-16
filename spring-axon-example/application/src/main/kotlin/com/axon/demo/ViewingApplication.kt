package com.message.viewing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("com.message")
class ViewingApplication

fun main(args: Array<String>) {
	runApplication<ViewingApplication>(*args)
}
