package com.matoatoa.cars.server.memory.data

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux.fromStream
import reactor.core.publisher.Mono.just
import reactor.core.publisher.Mono.justOrEmpty

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