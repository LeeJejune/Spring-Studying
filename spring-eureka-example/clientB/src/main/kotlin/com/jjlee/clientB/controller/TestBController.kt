package com.jjlee.clientB.controller

import com.jjlee.clientB.domain.TestPerson
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestBController {

    @GetMapping("/test")
    fun testPerson() : TestPerson{
        return TestPerson("jjlee", "i am jjlee")
    }
}