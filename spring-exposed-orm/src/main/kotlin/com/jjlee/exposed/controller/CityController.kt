package com.jjlee.exposed.controller

import com.jjlee.exposed.dto.request.CityCreateRequest
import com.jjlee.exposed.dto.response.CityResponse
import com.jjlee.exposed.service.CityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CityController(
    private val cityService: CityService
) {

    @PostMapping("/cities")
    fun createCity(@RequestBody request: CityCreateRequest) : Long{
        return cityService.create(request)
    }

    @GetMapping("/cities/{id}")
    fun getCity(@PathVariable id: Long) : CityResponse {
        return cityService.getCityById(id)
    }
}