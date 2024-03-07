package com.jjlee.eureka.clientA.controller

import com.jjlee.eureka.clientA.domain.TestCar
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestAController {

    @GetMapping("/test")
    fun test(): TestCar {
        return TestCar("grandeur", "it's a grandeur.")
    }
}