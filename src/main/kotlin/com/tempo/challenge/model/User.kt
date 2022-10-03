package com.tempo.challenge.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    var id: String,
    var firstName: String,
    var lastName: String,
    var displayName: String,
    var avatarUrl: String,
    var location: String,
//    @JsonBackReference
//    var role: Role,
//    @JsonBackReference
//    var team: Team,
)
