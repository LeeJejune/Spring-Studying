package com.jjl.sqs.sqs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SqsMessage {

    public SqsMessage(final SqsPayload payload, final SqsHeaders headers) {
        this.payload = payload;
        this.headers = headers;
    }

    public SqsMessage() {
    }

    @JsonProperty("payload")
    private SqsPayload payload;
    @JsonProperty("headers")
    private SqsHeaders headers;

    public SqsPayload getPayload() {
        return payload;
    }

    public SqsHeaders getHeaders() {
        return headers;
    }
}

class SqsHeaders{
    public SqsHeaders() {
    }

    public SqsHeaders(final String id, final Long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    @JsonProperty("id")
    private String id;
    @JsonProperty("timestamp")
    private Long timestamp;

    public String getId() {
        return id;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}

class SqsPayload{
    @JsonProperty("content")
    private String content;
    @JsonProperty("sequence")
    private Long sequence;

    public SqsPayload() {
    }

    public SqsPayload(final String content, final long sequence) {
        this.content = content;
        this.sequence = sequence;
    }

    public String getContent() {
        return content;
    }

    public Long getSequence() {
        return sequence;
    }
}
