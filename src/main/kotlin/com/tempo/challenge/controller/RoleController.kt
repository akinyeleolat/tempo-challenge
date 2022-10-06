package com.tempo.challenge.controller

import com.tempo.challenge.dto.RoleDTO
import com.tempo.challenge.model.Role
import com.tempo.challenge.service.RoleService
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
@RequestMapping("/role")
class RoleController(private val roleService: RoleService) {

    @GetMapping
    fun getRoleList():ResponseEntity<List<Role>>{
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles())
    }

    @PostMapping
    fun addRole(@Validated @RequestBody payload: RoleDTO):ResponseEntity<Role>{
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.addRole(payload))
    }
}