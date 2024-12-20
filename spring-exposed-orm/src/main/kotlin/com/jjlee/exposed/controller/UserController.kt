package com.jjlee.exposed.controller

import com.jjlee.exposed.dto.request.UserCreateRequest
import com.jjlee.exposed.dto.response.UserResponse
import com.jjlee.exposed.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: Long) : UserResponse {
        return userService.findUserById(id)
    }

    @PostMapping("/users")
    fun createUser(@RequestBody request: UserCreateRequest) : Long {
        return userService.create(request)
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.delete(id)
    }
}