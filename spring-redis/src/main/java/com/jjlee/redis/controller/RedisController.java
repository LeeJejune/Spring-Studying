package com.jjlee.redis.controller;

import com.jjlee.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {
    private final RedisService redisService;

    @PostMapping("/test")
    public void testSave(){
        redisService.save();
    }
}
