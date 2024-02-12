package com.example.flightkotlindemo.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.time.LocalDateTime


@Entity
@Table(name = "flights", uniqueConstraints = [UniqueConstraint(name = "flightName", columnNames = ["flightName"])])
data class Flight(

    @Id
    @GeneratedValue(generator = "task_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
    val id: Long = 0,

    @NotBlank
    @Column(name = "flightName", nullable = false, unique = true)
    var flightName: String = "",

    @Column(name = "flightFrom", nullable = false)
    var flightFrom: String = "",

    @Column(name = "flightTo", nullable = false)
    var flightTo: String = "",

    @Column(name = "flightDate", nullable = false)
    var flightDate: LocalDate =  LocalDateTime.now().toLocalDate(),

    @Column(name = "flightCarrier", nullable = false)
    var flightCarrier: String = "",
)
