package com.matoatoa.cars.server.memory.data

import reactor.core.publisher.Mono
import spock.lang.Specification

class VehicleRepositorySpec extends Specification {

    private static final I3 = new Vehicle(1, [], "",  VehicleModel.i3, [])
    private static final LEAF = new Vehicle(1, [], "",  VehicleModel.leaf, [])
    private static final TWIZY = new Vehicle(2, [], "",  VehicleModel.twizy, [])


    def "save with no previous data"() {
        given: "a repo"
        def repo = new VehicleRepository()

        expect: "repo is empty"
        repo.data.isEmpty()

        when: "car is saved"
        repo.save(I3)

        then: "repo contains car"
        repo.data.containsValue(I3)
    }

    def "save with previous data"() {
        given: "a repo"
        def repo = new VehicleRepository()

        and: "and some data is in the database"
        repo.data.put(1, LEAF)

        expect: "repo is not empty"
        repo.data.containsKey(1)

        when: "car is saved"
        repo.save(I3)

        then: "repo contains car"
        repo.data.containsValue(I3)
    }

    def "find by id with no previous data"() {
        given: "a repo"
        def repo = new VehicleRepository()

        expect: "repo is empty"
        repo.data.isEmpty()

        and: "nothing is found"
        repo.findById(1) == Mono.empty()
    }

    def "find by id with previous data"() {
        given: "a repo"
        def repo = new VehicleRepository()

        and: "car was saved before"
        repo.data.put(1, I3)

        expect: "repo is not empty"
        repo.data.containsKey(1)

        and: "we find the car"
        repo.findById(1).block() == I3
    }

    def "exists by id with no previous data"() {
        given: "a repo"
        def repo = new VehicleRepository()

        expect: "repo is empty"
        repo.data.isEmpty()

        and: "nothing is found"
        !repo.existsById(1).block()
    }

    def "exists by id with previous data"() {
        given: "a repo"
        def repo = new VehicleRepository()

        and: "car was saved before"
        repo.data.put(1, I3)

        expect: "repo is not empty"
        repo.data.containsKey(1)

        and: "we find the car"
        repo.existsById(1).block()
    }


    def "delete by id with no previous data"() {
        given: "a repo"
        def repo = new VehicleRepository()

        expect: "repo is empty"
        repo.data.isEmpty()

        and: "nothing is found"
        !repo.deleteById(1).block()
    }

    def "delete by id with previous data"() {
        given: "a repo"
        def repo = new VehicleRepository()

        and: "car was saved before"
        repo.data.put(1, I3)

        expect: "repo is not empty"
        repo.data.containsKey(1)

        when: "we can delete the car"
        repo.deleteById(1)

        then: "car is removed"
        !repo.data.containsKey(1)
    }

    def "delete all"() {
        given: "a repo"
        def repo = new VehicleRepository()

        and: "car was saved before"
        repo.data.put(1, I3)

        expect: "repo is not empty"
        repo.data.containsKey(1)

        when: "we delete all"
        repo.deleteAll()

        then:
        repo.data.isEmpty()
    }

    def "find all"() {
        given: "a repo"
        def repo = new VehicleRepository()

        and: "fill data"
        repo.data.putAll( [1:I3,2:TWIZY])

        expect:
        repo.findAll().collectList().block() == [I3,TWIZY]
    }
}
