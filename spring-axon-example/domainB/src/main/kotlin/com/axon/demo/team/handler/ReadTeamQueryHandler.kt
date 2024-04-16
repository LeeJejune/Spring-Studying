package com.message.viewing.team.handler

import com.message.viewing.common.ReadTeamQuery
import com.message.viewing.common.TeamDto
import com.message.viewing.team.domain.Team
import com.message.viewing.team.domain.TeamRepository
import org.axonframework.extensions.kotlin.query
import org.axonframework.queryhandling.QueryBus
import org.axonframework.queryhandling.QueryGateway
import org.axonframework.queryhandling.QueryHandler
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager
import java.util.logging.Logger

@Component
class ReadTeamQueryHandler(
    private val teamRepository: TeamRepository,
) {
    private val logger = LoggerFactory.getLogger(ReadTeamQueryHandler::class.java)
    @QueryHandler
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun handle(query: ReadTeamQuery): TeamDto {
        logger.info("team transactional : ${TransactionSynchronizationManager.getCurrentTransactionName()}")
        logger.info("current Thread : ${Thread.currentThread().name}")
        val team = teamRepository.findByIdOrNull(query.id) ?: throw IllegalArgumentException("Team not found")
        return TeamDto(team.id, team.name)
    }
}
