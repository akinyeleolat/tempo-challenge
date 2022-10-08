package com.tempo.challenge.controller

import com.tempo.challenge.dto.TeamMembershipListDTO
import com.tempo.challenge.dto.TeamRoleDTO
import com.tempo.challenge.dto.TeamRoleInputDTO
import com.tempo.challenge.model.TeamRole
import com.tempo.challenge.service.TeamRoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Controller for REST API endpoints
 */
@RestController
@RequestMapping("/teamrole")
class TeamRoleController(private val teamRoleService: TeamRoleService) {
    @PostMapping
    fun createTeamRole(@Validated @RequestBody payload: TeamRoleInputDTO): ResponseEntity<TeamRoleDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamRoleService.createTeamRole(payload))
    }

    @PostMapping("/find")
    fun findTeamRole(@Validated @RequestBody payload: TeamRoleInputDTO): ResponseEntity<TeamRoleDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(teamRoleService.getMembershipByRole(payload))
    }

    @GetMapping("/member/{roleId}")
    fun teamRoleMembership(@PathVariable("roleId") roleId: Long):ResponseEntity<TeamMembershipListDTO>{
        return ResponseEntity.status(HttpStatus.OK).body(teamRoleService.getRoleMembership(roleId))
    }
}