package com.matoatoa.cars.server.memory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MemoryServer

fun main(args: Array<String>) {
    runApplication<MemoryServer>(*args)
}
