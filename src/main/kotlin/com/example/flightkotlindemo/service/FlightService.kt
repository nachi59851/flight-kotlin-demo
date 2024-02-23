package com.example.flightkotlindemo.service

import com.example.flightkotlindemo.exception.FlightNotFoundException
import com.example.flightkotlindemo.model.Flight
import com.example.flightkotlindemo.model.FlightDto
import com.example.flightkotlindemo.model.FlightUpdateRequest
import com.example.flightkotlindemo.repository.FlightRepository
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import java.util.stream.Collectors
import kotlin.reflect.full.memberProperties

@Service
class FlightService(private val flightRepository: FlightRepository) {

    private fun convertEntityToDto(flight: Flight): FlightDto {
        return FlightDto(
            flight.id,
            flight.flightName,
            flight.flightFrom,
            flight.flightTo,
            flight.flightDate,
            flight.flightCarrier
        )
    }


    private fun valuesToEntity(flight: Flight, flightDto: FlightDto) {
        flight.flightName = flightDto.flightName
        flight.flightFrom = flightDto.flightFrom
        flight.flightTo = flightDto.flightTo
        flight.flightDate = flightDto.flightDate
        flight.flightCarrier = flightDto.flightCarrier
    }

    fun getAllFlights(): List<FlightDto> =
        flightRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList())

    fun getFlightById(id: Long): FlightDto {
        checkForFlightId(id)
        val task: Flight = flightRepository.findFlightById(id)
        return convertEntityToDto(task)
    }

    private fun checkForFlightId(id: Long) {
        if (!flightRepository.existsById(id)) {
            throw FlightNotFoundException("Flight with ID: $id does not exist!")
        }
    }

    fun createNewFlight(createRequest: FlightDto): FlightDto {
        val flight = Flight()
        valuesToEntity(flight, createRequest)
        val savedTask = flightRepository.save(flight)
        return convertEntityToDto(savedTask)
    }

    fun deleteFlight(id: Long): String {
        checkForFlightId(id)
        flightRepository.deleteById(id)
        return "Flight with id: $id has been deleted."
    }

    fun updateTask(id: Long, updateRequest: FlightUpdateRequest): FlightDto {
        checkForFlightId(id)
        val existingFlight: Flight = flightRepository.findFlightById(id)

        for (prop in FlightUpdateRequest::class.memberProperties) {
            if (prop.get(updateRequest) != null) {
                val field: Field? = ReflectionUtils.findField(Flight::class.java, prop.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingFlight, prop.get(updateRequest))
                }
            }
        }

        val savedFlight: Flight = flightRepository.save(existingFlight)
        return convertEntityToDto(savedFlight)
    }

    fun testFunction(){
        //Just Test it and delete
        //it
    }
}