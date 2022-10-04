package com.tempo.challenge.model

import javax.persistence.*

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
