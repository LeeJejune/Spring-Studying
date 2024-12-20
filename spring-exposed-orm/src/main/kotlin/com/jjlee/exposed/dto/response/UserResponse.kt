package com.jjlee.exposed.dto.response

data class UserResponse(
    val id: Long,
    val name: String,
    val city: CityResponse
)
