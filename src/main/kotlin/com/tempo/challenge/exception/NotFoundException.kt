package com.tempo.challenge.exception

import org.springframework.http.HttpStatus

class NotFoundException (val statusCode: HttpStatus, val reason: String) : Exception()