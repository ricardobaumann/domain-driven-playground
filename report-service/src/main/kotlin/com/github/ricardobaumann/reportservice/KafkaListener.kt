package com.github.ricardobaumann.reportservice

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class KafkaListener {

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["customer", "order"])
    fun listen(@Payload message: String, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) partitionId: Int) {
        logger.info("Receiving $message on partition $partitionId")

        //TODO process, index, and provide this data as reports
    }

}
