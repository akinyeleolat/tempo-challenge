package com.tempo.challenge.dto

import com.tempo.challenge.model.Role
import com.tempo.challenge.model.User
import org.jetbrains.annotations.NotNull

class TeamDTO(
    var id:String,
    var name: String,
)

class TeamRoleInputDTO(
    var roleId:Long?,
    @NotNull("user id required")
    var userId: String,
    @NotNull("team id required")
    var teamId: String,
)

class TeamRoleDTO(
    var roleId:Long,
    var userId: String,
    var teamId: String,
    var user: User?,
    var role: Role,
    var team: TeamDTO?
)
