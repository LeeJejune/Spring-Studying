package com.jjlee.exposed.domain

import org.jetbrains.exposed.dao.id.LongIdTable


object User : LongIdTable("users") {
    val name = varchar("name", 50)
    val cityId = reference("city_id", City)
}