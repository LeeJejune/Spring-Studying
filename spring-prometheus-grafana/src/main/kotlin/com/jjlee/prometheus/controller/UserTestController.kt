package com.jjlee.prometheus.controller

import com.jjlee.prometheus.service.UserTestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserTestController(
    private val userTestService: UserTestService
) {
    @GetMapping("/test")
    fun test() {
        userTestService.createUserTest()
    }
}