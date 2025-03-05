package com.jjlee.kafkaa

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/messages")
class MessageController(private val kafkaProducer: KafkaProducer) {

    @PostMapping
    fun publishMessage(@RequestBody request: MessageRequest): MessageResponse {
        val id = UUID.randomUUID().toString()
        val message = Message(id = id, content = request.content)
        kafkaProducer.sendMessage(message)
        return MessageResponse(success = true, messageId = id)
    }
}

data class MessageRequest(val content: String)
data class MessageResponse(val success: Boolean, val messageId: String)