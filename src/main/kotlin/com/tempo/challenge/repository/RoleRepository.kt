package com.tempo.challenge.repository

import com.tempo.challenge.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long>{
    fun findByName(name:String):Role?
}