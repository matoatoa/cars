package com.matoatoa.cars.server.memory.data


import reactor.core.publisher.Mono
import spock.lang.Specification

class CustomerRepositorySpec extends Specification {

    def "save with no previous data"() {
        given: "a repo"
        def repo = new CustomerRepository()

        and: "a sample"
        def customer = new Customer(1, "Jael", "Hauer", "Irgendwo")

        expect: "the repo is empty"
        repo.data.isEmpty()

        when: "customer is saved"
        repo.save(customer)

        then: "the map contains the value"
        repo.data.containsValue(customer)
    }

    def "save with previous data"() {
        given: "a repo"
        def repo = new CustomerRepository()

        and: "sample customer"
        def jael = new Customer(1, "Jael", "Hauer", "Irgendwo")
        def hans = new Customer(1, "Hans", "Müller", "Irgendwo")

        and:
        repo.data.put(1, hans)

        expect: "the repo contains key 1"
        repo.data.containsKey(1)

        when: "customer is saved"
        repo.save(jael)

        then: "the map contains the value"
        repo.data.containsValue(jael)
    }

    def "find by id with no data"() {
        given: "a repo"
        def repo = new CustomerRepository()

        expect: "the repo is empty"
        repo.data.isEmpty()

        and: "response is empty"
        repo.findById(1) == Mono.empty()
    }

    def "find by id with data"() {
        given: "a repo"
        def repo = new CustomerRepository()

        and: "a sample"
        def jael = new Customer(1, "Jael", "Hauer", "Irgendwo")

        and:
        repo.data.put(1, jael)

        expect: "the repo contains key 1"
        repo.data.containsKey(1)

        and: "response is jael"
        repo.findById(1).block() == jael
    }

    def "exists by id with no data"() {
        given: "a repo"
        def repo = new CustomerRepository()

        expect: "the repo is empty"
        repo.data.isEmpty()

        and: "response is empty"
        !repo.existsById(1).block()
    }

    def "exists by id with data"() {
        given: "a repo"
        def repo = new CustomerRepository()

        and: "a sample"
        def jael = new Customer(1, "Jael", "Hauer", "Irgendwo")

        and:
        repo.data.put(1, jael)

        expect: "the repo contains key 1"
        repo.data.containsKey(1)

        and: "response is true"
        repo.existsById(1).block()
    }

    def "delete by id with no data"() {
        given: "a repo"
        def repo = new CustomerRepository()

        expect: "the repo is empty"
        repo.data.isEmpty()

        and: "response is false"
        !repo.deleteById(1).block()
    }

    def "delete by id with data"() {
        given: "a repo"
        def repo = new CustomerRepository()

        and: "a sample"
        def jael = new Customer(1, "Jael", "Hauer", "Irgendwo")

        and: "set data"
        repo.data.put(1, jael)

        expect: "the repo contains key 1"
        repo.data.containsKey(1)

        and: "response is true"
        repo.deleteById(1).block()
    }

    def "delete all"() {
        given: "a repo"
        def repo = new CustomerRepository()

        and: "a sample"
        def jael = new Customer(1, "Jael", "Hauer", "Irgendwo")

        and: "set data"
        repo.data.put(1, jael)

        expect: "the repo contains a value"
        repo.data.containsValue(jael)

        when: "all is deleted"
        repo.deleteAll()

        then: "repo is empty"
        repo.data.isEmpty()
    }

    def "find all with no data"() {
        given: "a repo"
        def repo = new CustomerRepository()

        expect: "the repo is empty"
        repo.data.isEmpty()

        and: "response is empty"
        repo.findAll().collectList().block() == []
    }

    def "find all with data"() {
        given: "a repo"
        def repo = new CustomerRepository()

        and: "a sample"
        def jael = new Customer(1, "Jael", "Hauer", "Irgendwo")

        and: "set data"
        repo.data.put(1, jael)

        expect: "the repo contains a value"
        repo.data.containsValue(jael)

        and: "response is a list"
        repo.findAll().collectList().block() == [jael]
    }
}
