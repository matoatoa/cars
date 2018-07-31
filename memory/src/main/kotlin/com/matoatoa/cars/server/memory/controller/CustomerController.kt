package com.matoatoa.cars.server.memory.controller

import com.matoatoa.cars.server.memory.data.Customer
import com.matoatoa.cars.server.memory.data.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/customer/")
class CustomerController(@Autowired val repo: CustomerRepository) {

    @GetMapping("")
    fun getAll() = repo.findAll().map { list -> ok(list) }

    @DeleteMapping("")
    fun deleteAll() = repo.deleteAll().map { _ -> noContent() }

    @PutMapping("")
    fun save(@RequestBody customer: Customer) = repo.save(customer).map { _ -> noContent() }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int) = repo.findById(id)
            .map { entity -> ok(entity) }
            .defaultIfEmpty(notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) = repo.deleteById(id)
            .map { entity -> ok(entity) }
            .defaultIfEmpty(notFound().build())

    @PutMapping("/{id}/lastName")
    fun updateLastName(@PathVariable id: Int, @RequestBody lastName: String) =
            repo.findById(id)
                    .flatMap { entity -> repo.save(entity.withLastName(lastName)) }
                    .map { _ -> noContent() }
}