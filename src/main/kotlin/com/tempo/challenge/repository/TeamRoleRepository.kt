package com.tempo.challenge.repository

import com.tempo.challenge.model.TeamRole
import com.tempo.challenge.model.TeamRoleId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeamRoleRepository: JpaRepository<TeamRole, Long> {
}