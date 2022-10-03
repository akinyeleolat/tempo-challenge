package com.tempo.challenge.service

import com.tempo.challenge.configuration.ApiServiceConfiguration
import com.tempo.challenge.model.User
import com.tempo.challenge.model.Team
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

class WebClientService(
    private val webClient: WebClient,
    private val apiServiceConfig: ApiServiceConfiguration,
) {

    fun getUser(
        userId: String,
    ): ResponseEntity<User>? = webClient
        .get()
        .uri(UriComponentsBuilder
            .fromHttpUrl(apiServiceConfig.url)
            .path("/users/{id}")
            .buildAndExpand(userId)
            .toUri())
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
        .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
        .toEntity(User::class.java)
        .block()

    fun getTeam(
        teamId: String,
    ): ResponseEntity<Team>? = webClient
        .get()
        .uri(UriComponentsBuilder
            .fromHttpUrl(apiServiceConfig.url)
            .path("/teams/{id}")
            .buildAndExpand(teamId)
            .toUri())
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
        .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
        .toEntity(Team::class.java)
        .block()
}