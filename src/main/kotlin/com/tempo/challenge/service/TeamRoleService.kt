package com.tempo.challenge.service

import com.tempo.challenge.repository.TeamRoleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class TeamRoleService(
    private val teamRoleRepository: TeamRoleRepository
) {
}