package com.jjlee.exposed.domain

import org.jetbrains.exposed.dao.id.LongIdTable

object City : LongIdTable("cities") {
    val name = varchar("name", 50)
    val state = varchar("state", 50)
    val country = varchar("country", 50)
}