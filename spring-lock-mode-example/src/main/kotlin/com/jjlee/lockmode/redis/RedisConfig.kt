package com.jjlee.lockmode.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
    private val redisFactory: RedisConnectionFactory
) {
    @Bean
    fun <T> redisTemplate(): RedisTemplate<String, T> {
        val redisTemplate = RedisTemplate<String,T>()
        redisTemplate.connectionFactory = redisFactory
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()
        return redisTemplate
    }
}