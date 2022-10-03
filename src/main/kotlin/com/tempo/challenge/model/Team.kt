package com.tempo.challenge.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Team(
    var id: String,
    var name: String,
    var teamLeadId: String,
    var teamMemberIds: ArrayList<String>
//    @JsonBackReference
//    var teamLead: User,
//    @JsonBackReference
//    var teamMembers: ArrayList<User>
)
