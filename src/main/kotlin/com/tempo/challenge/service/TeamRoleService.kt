package com.tempo.challenge.service

import com.tempo.challenge.exception.ApiServiceException
import com.tempo.challenge.model.TeamRole
import com.tempo.challenge.repository.TeamRoleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class TeamRoleService(
    private val teamRoleRepository: TeamRoleRepository,
    private val roleService: RoleService,
    private val webClientService: WebClientService,
) {
    /**
     * Create Team Role.
     *
     * @param teamRole the teamRole
     * @return the teamRole
     */
    fun createTeamRole(teamRole: TeamRole): TeamRole = when{
        !roleService.checkRoleExist(teamRole.roleId) -> {
            throw ApiServiceException(HttpStatus.NOT_FOUND,"Role not found")
        }
        webClientService.getUser(teamRole.userId)?.hasBody() == false -> {
            throw ApiServiceException(HttpStatus.NOT_FOUND,"User not found")
        }
        webClientService.getTeam(teamRole.teamId)?.hasBody() == false -> {
            throw ApiServiceException(HttpStatus.NOT_FOUND,"Team not found")
        }

        else -> {
            teamRoleRepository.save(teamRole)
        }

    }
}