package com.jjl.sqs.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByHeaderId(String headerId);
}
