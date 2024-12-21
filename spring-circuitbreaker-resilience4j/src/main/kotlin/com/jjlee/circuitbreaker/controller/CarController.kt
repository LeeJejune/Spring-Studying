package com.jjlee.circuitbreaker.controller

import com.jjlee.circuitbreaker.service.CarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CarController(
    private val carService: CarService
) {
    @GetMapping("/cars/{id}/info")
    fun carInfo(@PathVariable id: String): String {
        return carService.getCarInfo(id)
    }
}