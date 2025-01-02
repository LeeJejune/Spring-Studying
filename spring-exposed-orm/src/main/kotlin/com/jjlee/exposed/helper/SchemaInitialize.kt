package com.jjlee.exposed.helper

import com.jjlee.exposed.domain.Cities
import com.jjlee.exposed.domain.Users
import org.jetbrains.exposed.sql.SchemaUtils
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class SchemaInitialize : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        SchemaUtils.create(Cities)
        SchemaUtils.create(Users)
    }
}