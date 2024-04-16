package com.message.viewing.team.service

import com.message.viewing.team.domain.TeamRepository
import org.axonframework.eventsourcing.eventstore.EventStore
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TeamService(
    private val teamRepository: TeamRepository,
    private val eventStore: EventStore
) {
    fun getTeam(id: String) = teamRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("Team not found")

    fun getEventsForTeam(id: String) {
        eventStore.readEvents(id).asStream().map { s -> s.payload }
    }
}