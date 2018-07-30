package com.matoatoa.cars.server.memory.data

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux.fromStream
import reactor.core.publisher.Mono.just
import reactor.core.publisher.Mono.justOrEmpty

@Component
class CustomerRepository {

    private val data = HashMap<Int, Customer>()

    fun save(entity: Customer) = just(data.set(entity.id, entity))

    fun findById(id: Int) = justOrEmpty(data[id])

    fun existsById(id: Int) = just(data.containsKey(id))

    fun findAll() = fromStream(data.values.stream())

    fun deleteById(id: Int) = justOrEmpty(data.remove(id))

    fun deleteAll() = just(data.clear())
}

data class Customer(val id: Int = -1, val firstName: String = "", val lastName: String = "", val address: String = "")