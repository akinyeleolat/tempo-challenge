package com.tempo.challenge.service

import com.tempo.challenge.exception.ApiServiceException
import com.tempo.challenge.repository.RoleRepository
import com.tempo.challenge.model.Role
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class RoleService(
    private val roleRepository: RoleRepository
) {
    /**
     * Gets roles by id.
     *
     * @param roleId the role id
     * @return the role by id
     * @throws NotFoundException the role not found exception
     */
    fun getRolesById(roleId: Long): Role = roleRepository.findById(roleId)
        .orElseThrow { ApiServiceException(HttpStatus.NOT_FOUND, "No matching role was found") }

    fun checkRoleExist(roleId: Long): Boolean = roleRepository.existsById(roleId)

}