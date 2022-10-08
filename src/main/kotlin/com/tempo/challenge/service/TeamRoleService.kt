package com.tempo.challenge.service

import com.tempo.challenge.dto.*
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
    fun createTeamRole(teamRoleInput: TeamRoleInputDTO): TeamRoleDTO {
        try {
            val user = webClientService.getUser(teamRoleInput.userId)
            val team = webClientService.getTeam(teamRoleInput.teamId)
            if(teamRoleInput.roleId == null){
                teamRoleInput.roleId = roleService.getRolesByName("Developer")?.id
            }
            val teamRole= TeamRole(
            teamId=teamRoleInput.teamId,
            roleId= teamRoleInput.roleId!!,
            userId=teamRoleInput.userId
            )

            when {
                !roleService.checkRoleExist(teamRole.roleId)->{
                    throw ApiServiceException(HttpStatus.NOT_FOUND, "Role not found")
                }

                teamRoleRepository.searchTeamRoleByUserId(userId = teamRole.userId) != null ->{
                    throw ApiServiceException(HttpStatus.CONFLICT, "User already assigned to team")
                }

                user?.hasBody() == false -> {
                    throw ApiServiceException(HttpStatus.NOT_FOUND, "User not found")
                }

                team?.hasBody() == false -> {
                    throw ApiServiceException(HttpStatus.NOT_FOUND, "Team not found")
                }

                else -> {
                    teamRoleRepository.save(teamRole)
                }
            }

            return TeamRoleDTO(
                roleId = teamRole.roleId,
                userId = teamRole.userId,
                teamId = teamRole.teamId,
                role = roleService.getRolesById(teamRole.roleId),
                team = team?.body?.let {
                    TeamDTO(
                        id = it.id,
                        name = it.name,
                    )
                },
                user = user?.body
            )
        } catch (e: Exception) {
            throw e
        }

    }

    fun getMembershipByRole(teamRoleInput: TeamRoleInputDTO): TeamRoleDTO{
        try {
            val user = webClientService.getUser(teamRoleInput.userId)
            val team = webClientService.getTeam(teamRoleInput.teamId)
            when{
                user?.hasBody() == false -> {
                    throw ApiServiceException(HttpStatus.NOT_FOUND, "User not found")
                }

                team?.hasBody() == false -> {
                    throw ApiServiceException(HttpStatus.NOT_FOUND, "Team not found")
                }
                else ->{
                    val teamRole: TeamRole = teamRoleRepository.searchTeamRoleByUserIdAndTeamId(
                        userId = teamRoleInput.userId,
                        teamId = teamRoleInput.teamId
                    ) ?: throw ApiServiceException(HttpStatus.NOT_FOUND, "Role not found")
                        return TeamRoleDTO(
                            roleId = teamRole.roleId,
                            userId = teamRole.userId,
                            teamId = teamRole.teamId,
                            role = roleService.getRolesById(teamRole.roleId),
                            team = team?.body?.let {
                                TeamDTO(
                                    id = it.id,
                                    name = it.name,
                                )
                            },
                            user = user?.body
                        )


                }
            }
        }catch (e: Exception){
            throw e
        }
    }

    fun getRoleMembership(roleId:Long): TeamMembershipListDTO {
        try {
            when{
                !roleService.checkRoleExist(roleId = roleId)->{
                    throw ApiServiceException(HttpStatus.NOT_FOUND, "Role not found")
                }

                else ->{
                    val teamRoleList: List<TeamRole> = teamRoleRepository.findAllByRoleId(roleId)
                    val teamRoleListOutput= ArrayList<TeamRoleMembershipDTO>()
                    for(teamRole: TeamRole in teamRoleList){
                        teamRoleListOutput.add(this.toTeamRoleMembershipDTO(teamRole))
                    }
                    return TeamMembershipListDTO(
                        role = roleService.getRolesById(roleId),
                        membershipList=teamRoleListOutput
                    )
                }

            }

        }catch (e: Exception) {
            throw e
        }
    }

    fun toTeamRoleMembershipDTO(teamRole: TeamRole):TeamRoleMembershipDTO{
        val user = webClientService.getUser(teamRole.userId)
        val team = webClientService.getTeam(teamRole.teamId)
        return TeamRoleMembershipDTO(
            team = team?.body?.let {
                TeamDTO(
                    id = it.id,
                    name = it.name,
                )
            },
            user = user?.body
        )
    }
}