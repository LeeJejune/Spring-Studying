package com.jjlee.exposed.service

import com.jjlee.exposed.domain.Cities
import com.jjlee.exposed.domain.Users
import com.jjlee.exposed.dto.request.UserCreateRequest
import com.jjlee.exposed.dto.response.CityResponse
import com.jjlee.exposed.dto.response.UserResponse
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class UserService {

    fun findUserById(id: Long): UserResponse {
        val user = transaction {
            addLogger(StdOutSqlLogger)
            (Users leftJoin Cities).selectAll().where { Users.id eq id }.firstOrNull()
        }

        return if (user != null) {
            UserResponse(
                id = user[Users.id].value,
                name = user[Users.name],
                city = CityResponse(
                    id = user[Users.cityId].value,
                    name = user[Cities.name],
                    state = user[Cities.state],
                    country = user[Cities.country]
                )
            )
        } else {
            throw IllegalArgumentException("User not found")
        }
    }


    fun create(request: UserCreateRequest): Long {
        return transaction {
            addLogger(StdOutSqlLogger)
            val city = Cities.selectAll().where { Cities.id eq request.cityId }.firstOrNull()
                ?: throw IllegalArgumentException("City not found")
            val userId =  Users.insertAndGetId {
                it[name] = request.name
                it[cityId] = city[Cities.id]
            }
            userId.value
        }
    }

    fun delete(id: Long) {
        Users.deleteWhere { Users.id eq id }
    }
}