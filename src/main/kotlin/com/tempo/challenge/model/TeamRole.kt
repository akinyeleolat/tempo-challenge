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
    val id: Long?,
    @Column(name = "team_id", nullable = false)
    var teamId: String,
    @Column(name = "user_id", nullable = false)
    var userId: String,

    @JsonBackReference
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "role_id")
    var role: Role,
)