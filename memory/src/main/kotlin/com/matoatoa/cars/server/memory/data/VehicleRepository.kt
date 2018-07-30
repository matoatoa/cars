package com.matoatoa.cars.server.memory.data

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux.fromStream
import reactor.core.publisher.Mono.just
import reactor.core.publisher.Mono.justOrEmpty
import java.time.ZonedDateTime

@Component
class VehicleRepository {

    private val data = HashMap<Int, Vehicle>()

    fun save(entity: Vehicle) = just(data.set(entity.id, entity))

    fun findById(id: Int) = justOrEmpty(data[id])

    fun existsById(id: Int) = just(data.containsKey(id))

    fun findAll() = fromStream(data.values.stream())

    fun deleteById(id: Int) = justOrEmpty(data.remove(id))

    fun deleteAll() = just(data.clear())
}

data class Vehicle(val id: Int = -1, val owner : List<Customer> = emptyList(), val licencePlate: String = "", val model : VehicleModel = VehicleModel.leaf, val serviceDates : List<ZonedDateTime> = emptyList())

enum class VehicleModel (val price: Double) {
    twizy (16000.12), i3(12342.12), leaf(25000.00)
}