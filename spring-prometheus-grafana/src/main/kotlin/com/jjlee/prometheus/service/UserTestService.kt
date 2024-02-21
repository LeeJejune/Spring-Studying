package com.jjlee.prometheus.service

import com.jjlee.prometheus.domain.User
import com.jjlee.prometheus.domain.UserRepository
import org.springframework.stereotype.Service

@Service
class UserTestService(
    private val userRepository: UserRepository
) {
    fun createUserTest() {
        userRepository.save(User("test"))
    }
}