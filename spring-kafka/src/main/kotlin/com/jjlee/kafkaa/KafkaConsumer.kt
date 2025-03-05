package com.jjlee.kafkaa

import com.jjlee.kafkaa.config.KafkaConfig
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener

class KafkaConsumer {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [KafkaConfig.EXAMPLE_TOPIC], groupId = "example-group")
    fun consume(message: Message) {
        logger.info("Consumed message: $message")
    }
}