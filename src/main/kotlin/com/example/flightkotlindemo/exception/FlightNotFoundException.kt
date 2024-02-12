package com.example.flightkotlindemo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
data class FlightNotFoundException(
    override val message: String
): RuntimeException()
