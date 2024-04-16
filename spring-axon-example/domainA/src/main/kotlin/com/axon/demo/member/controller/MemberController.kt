package com.message.viewing.member.controller

import com.message.viewing.common.TeamDto
import com.message.viewing.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/members/{teamId}")
    fun getTeam(@PathVariable teamId: String) : ResponseEntity<TeamDto> {
        return ResponseEntity.ok(memberService.getTeam(teamId))
    }
}