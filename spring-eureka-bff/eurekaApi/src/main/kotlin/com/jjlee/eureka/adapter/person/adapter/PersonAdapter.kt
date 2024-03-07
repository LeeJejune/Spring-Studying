package com.jjlee.eureka.adapter.person.adapter

import com.jjlee.eureka.adapter.person.domain.Person
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class PersonAdapter {

    fun getPerson(): Person {
        val webclient = WebClient.create("http://localhost:9092")

        val person =  webclient.get().uri("/test")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Person::class.java)

        return person.block()!!
    }
}