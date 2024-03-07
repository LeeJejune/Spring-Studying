package com.jjlee.eureka.service

import com.jjlee.eureka.adapter.car.adapter.CarAdapter
import com.jjlee.eureka.adapter.person.adapter.PersonAdapter
import com.jjlee.eureka.dto.res.PersonCarResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
class TestApiService(
    private val carAdapter: CarAdapter,
    private val personAdapter: PersonAdapter
) {
    suspend fun getPersonCar(): PersonCarResponse = coroutineScope {
        val person = async {
            personAdapter.getPerson()
        }
        val car = async {
            carAdapter.getCar()
        }
        PersonCarResponse(
            car.await().name,
            car.await().description,
            person.await().name,
            person.await().description
        )
    }
}