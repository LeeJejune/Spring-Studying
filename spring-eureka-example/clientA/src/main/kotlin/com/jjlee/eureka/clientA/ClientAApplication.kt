package com.jjlee.eureka.clientA

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class ClientAApplication

fun main(args: Array<String>) {
    runApplication<ClientAApplication>(*args)
}