package com.jjlee.kafkaa.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaConfig {

    companion object {
        const val EXAMPLE_TOPIC = "example-topic"
    }

    @Bean
    fun exampleTopic(): NewTopic {
        return TopicBuilder.name(EXAMPLE_TOPIC)
            .partitions(1)
            .replicas(1)
            .build()
    }
}