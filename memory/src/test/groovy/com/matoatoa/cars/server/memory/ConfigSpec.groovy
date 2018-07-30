package com.matoatoa.cars.server.memory

import com.matoatoa.cars.server.memory.data.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@SpringBootTest
@Title("ConfigSpec")
@Narrative("Check the expected bean")
class ConfigSpec extends Specification {

    @Autowired (required = false)
    private CustomerRepository customerRepository

    def "customer repository"() {
        expect:
        customerRepository
    }
}
