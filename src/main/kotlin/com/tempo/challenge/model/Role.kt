package com.tempo.challenge.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * Represents the database entity for storing the role details.
 */
@Entity
@Table(name = "role")
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "name", unique = true, nullable = false)
    var name: String,
)
