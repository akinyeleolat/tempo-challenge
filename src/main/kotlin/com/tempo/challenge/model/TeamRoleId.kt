package com.tempo.challenge.model

import java.io.Serializable
import javax.persistence.*
@Embeddable
class TeamRoleId (private val userId: String, private val roleId: Long, private val teamId: String) : Serializable