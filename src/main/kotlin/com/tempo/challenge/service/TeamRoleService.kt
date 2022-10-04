package com.tempo.challenge.service

import com.tempo.challenge.dto.TeamDTO
import com.tempo.challenge.dto.TeamRoleDTO
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
    fun createTeamRole(teamRole: TeamRole): TeamRoleDTO {
        val user = webClientService.getUser(teamRole.userId)
        val team = webClientService.getTeam(teamRole.teamId)
        when{
        !roleService.checkRoleExist(teamRole.roleId) -> {
            throw ApiServiceException(HttpStatus.NOT_FOUND,"Role not found")
        }
        user?.hasBody() == false -> {
            throw ApiServiceException(HttpStatus.NOT_FOUND,"User not found")
        }
        team?.hasBody() == false -> {
            throw ApiServiceException(HttpStatus.NOT_FOUND,"Team not found")
        }
        else -> {
            teamRoleRepository.save(teamRole)
        }}

            return         TeamRoleDTO(
            roleId =teamRole.roleId,
            userId =teamRole.userId,
            teamId =teamRole.teamId,
            role = roleService.getRolesById(teamRole.roleId),
            team = team?.body?.let {
                TeamDTO(
                    id= it.id,
                    name=it.name,
                )
            },
            user = user?.body
        )
        }

}