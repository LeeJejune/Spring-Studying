package com.jjlee.clientB

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@SpringBootApplication
@EnableDiscoveryClient
class ClientBApplication

fun main(args: Array<String>) {
    runApplication<ClientBApplication>(*args)
}