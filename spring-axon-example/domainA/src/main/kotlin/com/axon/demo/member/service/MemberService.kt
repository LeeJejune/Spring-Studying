package com.message.viewing.member.service

import com.message.viewing.common.ReadTeamQuery
import com.message.viewing.common.TeamDto
import com.message.viewing.member.domain.MemberRepository
import org.axonframework.extensions.kotlin.query
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@Service
class MemberService(
    private val queryGateway: QueryGateway,
    private val memberRepository: MemberRepository
) {

    private val logger = LoggerFactory.getLogger(MemberService::class.java)

    @Transactional
    fun getTeam(teamId: String): TeamDto {
        logger.info("top transactional : ${TransactionSynchronizationManager.getCurrentTransactionName()}")
        val member = memberRepository.findById(1L).orElseThrow { IllegalArgumentException("Member not found") }
        logger.info("member transactional : ${TransactionSynchronizationManager.getCurrentTransactionName()}")
        val fetchTeamInfo = queryGateway.query<TeamDto, ReadTeamQuery>(ReadTeamQuery(teamId)).join()
        return fetchTeamInfo
    }
}