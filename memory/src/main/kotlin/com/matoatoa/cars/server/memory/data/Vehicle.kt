package com.matoatoa.cars.server.memory.data

import java.time.ZonedDateTime


data class Vehicle(val id: Int = -1, val owner : List<Customer> = emptyList(), val licencePlate: String = "", val model : VehicleModel = VehicleModel.leaf, val serviceDates : List<ZonedDateTime> = emptyList())

enum class VehicleModel (val price: Double) {
    twizy (16000.12), i3(12342.12), leaf(25000.00)
}