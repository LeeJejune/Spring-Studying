package com.jjlee.eureka.controller

import com.jjlee.eureka.dto.res.PersonCarResponse
import com.jjlee.eureka.service.TestApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestApiController (
    private val testApiService: TestApiService
){
    @GetMapping("/test")
    suspend fun test(): PersonCarResponse {
        return testApiService.getPersonCar()
    }
}