package com.jjl.sqs.sqs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerApi {
    private final SqsProducer sqsProducer;

    public ProducerApi(final SqsProducer sqsProducer) {
        this.sqsProducer = sqsProducer;
    }

    @GetMapping("/{message}")
    public void send(@PathVariable final String message){
        sqsProducer.sendMessage(message);
    }

    @GetMapping("/fifo/{message}")
    public void sendFifo(@PathVariable final String message, @RequestParam final String groupId, @RequestParam final String duplicationId) {
        sqsProducer.sendFifoMessage(message, groupId, duplicationId);
    }
}
