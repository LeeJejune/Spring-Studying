package com.jjl.sqs.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.jjl.sqs.event.Event;
import com.jjl.sqs.event.EventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

@Service
public class SqsConsumer {
    private final AmazonSQSAsync amazonSQSAsync;
    private final EventRepository eventRepository;
    private final String queueUrl;

    public SqsConsumer(final AmazonSQSAsync amazonSQSAsync, final EventRepository eventRepository,
                       @Value("${cloud.aws.sqs.queue.url}") final String queueUrl) {
        this.amazonSQSAsync = amazonSQSAsync;
        this.eventRepository = eventRepository;
        this.queueUrl = queueUrl;
    }

    public void doSomething(final SqsMessage message, final Acknowledgment ack) {
        final Event existingEvent = eventRepository.findByHeaderId(message.getHeaders().getId());

        if (null != existingEvent) {
            System.out.println("Duplicated " + message.getHeaders().getId());
            ack.acknowledge();
            return;
        }

        final Event newEvent = new Event(message.getPayload().getContent(), message.getPayload().getSequence(),
                message.getHeaders().getId(), message.getHeaders().getTimestamp());
        eventRepository.save(newEvent);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                if (!"world".equals(message.getPayload().getContent())) {
                    ack.acknowledge();
                }
            }
        });
    }

    @Transactional
    @SqsListener(value = "${cloud.aws.sqs.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listen(@Payload final SqsMessage message, @Headers final Map<String, String> headers, final Acknowledgment ack) {
        doSomething(message, ack);
    }

    @Transactional
    @SqsListener(value = "${cloud.aws.sqs.fifo-queue.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listenFifo(@Payload final SqsMessage message, @Headers final Map<String, String> headers, final Acknowledgment ack) {
        doSomething(message, ack);
    }
}
