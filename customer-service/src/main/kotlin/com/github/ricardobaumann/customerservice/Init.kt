package com.github.ricardobaumann.customerservice

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Init(private val kafkaTemplate: KafkaTemplate<String,String>,
           private val customerRepo: CustomerRepo) : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
       kafkaTemplate.send("customer", """
            "name": "joe"
        """.trimIndent())
                .addCallback(
                    {result -> logger.info("Success ${result?.recordMetadata?.offset()}")},
                    {exception -> logger.error("Failed to send due to ${exception.message}")})

        customerRepo.save(Customer(name = "test"))
    }
}
