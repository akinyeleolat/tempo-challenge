package com.tempo.challenge.model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

/**
 * Represents the database entity for storing the role details.
 */
@Entity
@Table(name = "teamrole")
data class TeamRole(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(name = "team_id", nullable = false)
    var teamId: String,
    @Column(name = "user_id", nullable = false)
    var userId: String,

    @JoinColumn(name = "role_id")
    var roleId: Long,
)