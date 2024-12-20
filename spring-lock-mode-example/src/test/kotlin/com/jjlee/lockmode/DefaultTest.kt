package com.jjlee.lockmode

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName

@SpringBootTest(classes = [LockmodeApplication::class])
abstract class DefaultTest : FunSpec() {

    override fun extensions() = listOf(SpringExtension)

    companion object {
        @JvmField
        @Container
        val redis = GenericContainer(DockerImageName.parse("redis:5.0.3-alpine")).withExposedPorts(6379)

        @DynamicPropertySource
        @JvmStatic
        fun registerRedisProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.redis.host", redis::getHost)
            registry.add("spring.data.redis.port") { redis.getMappedPort(6379).toString() }
        }
        init {
            redis.start()
        }
    }
}
