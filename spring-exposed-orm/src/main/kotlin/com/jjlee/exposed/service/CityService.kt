package com.jjlee.exposed.service

import com.jjlee.exposed.domain.City
import com.jjlee.exposed.dto.request.CityCreateRequest
import com.jjlee.exposed.dto.response.CityResponse
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class CityService {

    fun create(request: CityCreateRequest) : Long {
        return transaction {
            addLogger(StdOutSqlLogger)
            val id = City.insertAndGetId {
                it[name] = request.name
                it[state] = request.state
                it[country] = request.country
            }
            id.value
        }
    }

    fun getCityById(id: Long) : CityResponse {
        val city= transaction {
            addLogger(StdOutSqlLogger)
            City.selectAll().where { City.id eq id }.firstOrNull()
        }
        if (city != null) {
            return CityResponse(
                id = city[City.id].value,
                name = city[City.name],
                state = city[City.state],
                country = city[City.country]
            )
        } else {
            throw IllegalArgumentException("City not found")
        }
    }
}