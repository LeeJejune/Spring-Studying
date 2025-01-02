package com.jjlee.exposed.domain

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object Cities : LongIdTable("cities") {
    val name = varchar("name", 50)
    val state = varchar("state", 50)
    val country = varchar("country", 50)
}

class City(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<City>(Cities)

    var name by Cities.name
    var state by Cities.state
    var country by Cities.country
}