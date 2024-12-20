package com.jjlee.exposed.dto.request

data class CityCreateRequest(
    val name: String,
    val state: String,
    val country: String
) {
}