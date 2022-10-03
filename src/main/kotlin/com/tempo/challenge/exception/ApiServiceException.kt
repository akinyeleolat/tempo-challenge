package com.tempo.challenge.exception

import org.springframework.http.HttpStatus

class ApiServiceException(val statusCode: HttpStatus, val reason: String) : Exception()