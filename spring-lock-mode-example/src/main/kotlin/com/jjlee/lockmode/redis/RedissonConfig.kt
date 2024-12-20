package com.jjlee.lockmode.redis

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RedissonConfig {
    @Value("\${spring.data.redis.host}")
    private val redisHost: String? = null

    @Value("\${spring.data.redis.port}")
    private val redisPort = 0

    @Bean
    fun redissonClient(): RedissonClient? {
        var redisson: RedissonClient? = null
        val config = Config()
        config.useSingleServer().setAddress("$REDISSON_HOST_PREFIX$redisHost:$redisPort")
        redisson = Redisson.create(config)
        return redisson
    }

    companion object {
        private const val REDISSON_HOST_PREFIX = "redis://"
    }
}