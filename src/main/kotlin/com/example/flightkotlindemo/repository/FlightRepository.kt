package com.example.flightkotlindemo.repository

import com.example.flightkotlindemo.model.Flight
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository: JpaRepository<Flight, Long> {

    fun findFlightById(id: Long): Flight
}