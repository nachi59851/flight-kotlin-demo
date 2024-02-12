package com.example.flightkotlindemo.controller

import com.example.flightkotlindemo.model.Flight
import com.example.flightkotlindemo.model.FlightDto
import com.example.flightkotlindemo.model.FlightUpdateRequest
import com.example.flightkotlindemo.service.FlightService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class FlightController(private val flightService: FlightService) {

    @GetMapping("/flights")
    //fun getAllArticles(): List<Article> = articleRepository.findAll()
    fun getAllFlights(): ResponseEntity<List<FlightDto>> = ResponseEntity(flightService.getAllFlights(), HttpStatus.OK)

    @PostMapping("/create")
    fun createTask(
        @Valid @RequestBody createRequest: FlightDto
    ): ResponseEntity<FlightDto> = ResponseEntity(flightService.createNewFlight(createRequest), HttpStatus.OK)

    @GetMapping("/flight/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<FlightDto> = ResponseEntity(flightService.getFlightById(id), HttpStatus.OK)

    @DeleteMapping("/del/flight/{id}")
    fun deleteFlight(@PathVariable id: Long): ResponseEntity<String> = ResponseEntity(flightService.deleteFlight(id), HttpStatus.OK)

    @PatchMapping("/update/{id}")
    fun updateTask(
        @PathVariable id: Long,
        @Valid @RequestBody updateRequest: FlightUpdateRequest
    ): ResponseEntity<FlightDto> = ResponseEntity(flightService.updateTask(id, updateRequest), HttpStatus.OK)

}