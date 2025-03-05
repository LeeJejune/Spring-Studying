package com.jjlee.kafkaa

import com.jjlee.kafkaa.config.KafkaConfig
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun sendMessage(message: Message) {
        logger.info("Producing message: $message")
        val future = kafkaTemplate.send(KafkaConfig.EXAMPLE_TOPIC, message.id, message)

        future.whenComplete { result, ex ->
            if (ex == null) {
                logger.info("Message sent successfully: ${result.recordMetadata}")
            } else {
                logger.error("Failed to send message", ex)
            }
        }
    }
}