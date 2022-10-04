package com.tempo.challenge.model

import java.io.Serializable
import javax.persistence.*
@Embeddable
class TeamRoleId (private val userId: String, private val roleId: Long, private val teamId: String) : Serializable

/**
 * Represents the database entity for storing the team role details.
 */
@Entity
@IdClass(TeamRoleId::class)
@Table(name = "teamrole")
data class TeamRole(
    @Id
    @Column(name = "team_id", nullable = false)
    var teamId: String,
    @Id
    @Column(name = "user_id", nullable = false)
    var userId: String,

    @Id
    @JoinColumn(name = "role_id")
    var roleId: Long,
)
