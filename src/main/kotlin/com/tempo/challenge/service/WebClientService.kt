package com.tempo.challenge.service

import com.tempo.challenge.configuration.CgjressApiConfig
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
    private val apiServiceConfig: CgjressApiConfig,
) {

    fun getUserAsynchronous(
        userId: String,
    ): Mono<User> = webClient
        .get()
        .uri(UriComponentsBuilder
            .fromHttpUrl(apiServiceConfig.url)
            .path("/users/")
            .queryParam("id",userId)
            .build()
            .toUri())
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
        .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
        .bodyToMono(User::class.java)

    fun getTeamAsynchronous(
        teamId: String,
    ): Mono<Team> = webClient
        .get()
        .uri(UriComponentsBuilder
            .fromHttpUrl(apiServiceConfig.url)
            .path("/teams/")
            .queryParam("id",teamId)
            .build()
            .toUri())
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
        .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
        .bodyToMono(Team::class.java)
}