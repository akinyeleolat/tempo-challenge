package com.tempo.challenge.controller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.tempo.challenge.dto.TeamRoleInputDTO
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class TeamRoleControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
) {
    val baseUrl="/teamrole"
    @Nested
    @DisplayName("POST /teamrole")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class CreateTeamRole{
        @Test
        fun `should add team role`() {
            val newTeamRole = TeamRoleInputDTO(1,"fa1529de-5f20-49a7-ad25-a494008dd322","29191298-248d-48d7-8a6a-0bfecbc75f14")
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newTeamRole)
            }
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                }
        }


        @Test
        fun `should add team role with default role when role not supplied`() {
            val newTeamRole = TeamRoleInputDTO(null, "fa1529de-5f20-49a7-ad25-a494008dd322","29191298-248d-48d7-8a6a-0bfecbc75f14")
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newTeamRole)
            }
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                }
        }


        @Test
        fun `should return 404 if the role does not exist`() {
            val newTeamRole = TeamRoleInputDTO(10, "fa1529de-5f20-49a7-ad25-a494008dd322","29191298-248d-48d7-8a6a-0bfecbc75f14")
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newTeamRole)
            }
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }

        @Test
        fun `should return 404 if the user does not exist`() {
            val newTeamRole = TeamRoleInputDTO(1, "fa1529de-5f20-49a7-ad25-a494008dd323","29191298-248d-48d7-8a6a-0bfecbc75f14")
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newTeamRole)
            }
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }


        @Test
        fun `should return 404 if the team does not exist`() {
            val newTeamRole = TeamRoleInputDTO(1, "fa1529de-5f20-49a7-ad25-a494008dd323","29191298-248d-48d7-8a6a-0bfecbc75f15")
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newTeamRole)
            }
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }

        @Test
        fun `should return 400 values not missing`() {
            val newTeamRole = TeamRoleInputDTO(null, "","")
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newTeamRole)
            }
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }

}