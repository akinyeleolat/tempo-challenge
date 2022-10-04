package com.tempo.challenge.dto

import com.tempo.challenge.model.Role
import com.tempo.challenge.model.User

class TeamDTO(
    var id:String,
    var name: String,
)

class TeamRoleDTO(
    var roleId:Long,
    var userId: String,
    var teamId: String,
    var user: User?,
    var role: Role,
    var team: TeamDTO?
)
