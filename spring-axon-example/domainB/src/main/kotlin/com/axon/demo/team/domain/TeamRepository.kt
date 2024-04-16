package com.message.viewing.team.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, String> {
}