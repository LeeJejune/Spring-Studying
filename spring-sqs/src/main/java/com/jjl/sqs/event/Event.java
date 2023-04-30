package com.jjl.sqs.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long sequence;
    private String headerId;
    private Long timestamp;

    public Event(final String name, final Long sequence, final String headerId, final Long timestamp) {
        this.name = name;
        this.sequence = sequence;
        this.headerId = headerId;
        this.timestamp = timestamp;
    }

    public Event() {

    }
}
