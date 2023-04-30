package com.jjl.sqs.sqs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SqsProducer {
    private final QueueMessagingTemplate queueMessagingTemplate;
    private final String fifoName;
    private final AtomicLong counter = new AtomicLong();
    private String queueName;

    public SqsProducer(final QueueMessagingTemplate queueMessagingTemplate,
                       @Value("${cloud.aws.sqs.fifo-queue.name}") final String fifoName) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.fifoName = fifoName;
    }

    @Value("${cloud.aws.sqs.queue.name}")
    public void setQueueName(final String queueName) {
        this.queueName = queueName;
    }

    public void sendMessage(final String message) {
        final SqsPayload sqsMessage = new SqsPayload(message, counter.getAndIncrement());
        final Message<SqsPayload> newMessage = MessageBuilder.withPayload(sqsMessage).build();
        queueMessagingTemplate.convertAndSend(queueName, newMessage);
    }

    public void sendFifoMessage(final String message, final String groupId, final String duplicationId) {
        final Map<String, Object> headers = new HashMap<>();
        headers.put("message-group-id", groupId);
        headers.put("message-deduplication-id", duplicationId);

        final SqsPayload sqsMessage = new SqsPayload("FIFO-" + message, counter.getAndIncrement());
        final Message<SqsPayload> newMessage = MessageBuilder.withPayload(sqsMessage)
                .build();
        queueMessagingTemplate.convertAndSend(fifoName, newMessage, headers);
    }
}
