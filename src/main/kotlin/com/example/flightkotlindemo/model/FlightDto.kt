package com.example.flightkotlindemo.model

import java.time.LocalDate

data class FlightDto(
    val id: Long,
    val flightName: String,
    val flightFrom: String,
    val flightTo: String,
    val flightDate: LocalDate,
    val flightCarrier: String,
)
