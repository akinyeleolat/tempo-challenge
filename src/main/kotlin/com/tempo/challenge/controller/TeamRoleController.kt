package com.tempo.challenge.controller

import com.tempo.challenge.model.TeamRole
import com.tempo.challenge.service.TeamRoleService
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
    fun createTeamRole(@RequestBody payload: TeamRole): TeamRole = teamRoleService.createTeamRole(payload)
}