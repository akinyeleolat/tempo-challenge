package com.tempo.challenge.repository

import com.tempo.challenge.model.TeamRole
import com.tempo.challenge.model.TeamRoleId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeamRoleRepository: JpaRepository<TeamRole, Long> {
    fun findByTeamId(teamId:String):TeamRole?
    fun findAllByTeamId(teamId:String):TeamRole?
    fun findAllByRoleId(roleId: Long): List<TeamRole>
    fun searchTeamRoleByUserIdAndTeamId(userId: String, teamId: String): TeamRole?
    fun searchTeamRoleByUserId(userId:String):TeamRole?
}