package com.jjlee.eureka.adapter.car.adapter

import com.jjlee.eureka.adapter.car.domain.Car
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class CarAdapter {

    fun getCar(): Car {
        val webclient = WebClient.create("http://localhost:9091")

        val car = webclient.get().uri("/test")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Car::class.java)

        return car.block()!!
    }
}