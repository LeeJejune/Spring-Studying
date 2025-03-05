package com.jjlee.kafkaa

import java.time.Instant

data class Message(
    val id: String,
    val content: String,
    val timestamp: Instant = Instant.now()
)