package com.jjlee.exposed.domain

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable


object Users : LongIdTable("users") {
    val name = varchar("name", 50)
    val cityId = reference("city_id", Cities)
}

class User(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<User>(Users)

    var name by Users.name
    var city by City referencedOn Users.cityId
}