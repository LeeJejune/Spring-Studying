package com.jjlee.exposed.service

import com.jjlee.exposed.domain.City
import com.jjlee.exposed.domain.User
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
            (User leftJoin City).selectAll().where { User.id eq id }.firstOrNull()
        }

        return if (user != null) {
            UserResponse(
                id = user[User.id].value,
                name = user[User.name],
                city = CityResponse(
                    id = user[User.cityId].value,
                    name = user[City.name],
                    state = user[City.state],
                    country = user[City.country]
                )
            )
        } else {
            throw IllegalArgumentException("User not found")
        }
    }


    fun create(request: UserCreateRequest): Long {
        return transaction {
            addLogger(StdOutSqlLogger)
            val city = City.selectAll().where { City.id eq request.cityId }.firstOrNull()
                ?: throw IllegalArgumentException("City not found")
            val userId =  User.insertAndGetId {
                it[name] = request.name
                it[cityId] = city[City.id]
            }
            userId.value
        }
    }

    fun delete(id: Long) {
        User.deleteWhere { User.id eq id }
    }
}