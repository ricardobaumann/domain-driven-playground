package com.github.ricardobaumann.orderservice

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class DomainEventRepo(private val kafkaTemplate: KafkaTemplate<String,String>,
                      private val objectMapper: ObjectMapper) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun created(id: String, resourceType: ResourceType, payload: Any?) {
        publish(id, payload, resourceType, EventType.CREATED)
    }

    fun updated(id: String, resourceType: ResourceType, payload: Any?) {
        publish(id, payload, resourceType, EventType.UPDATED)
    }

    fun deleted(id: String, resourceType: ResourceType) {
        publish(id, null, resourceType, EventType.DELETED)
    }

    private fun publish(id: String, payload: Any?, resourceType: ResourceType, eventType: EventType) {
        kafkaTemplate.send("order", objectMapper.writeValueAsString(
                DomainEvent(
                    action = eventType,
                    id = id,
                        resourceType = resourceType,
                    payload = payload
        ))).addCallback(
                {result -> logger.info("Success ${result?.recordMetadata?.offset()}")},
                {exception -> logger.error("Failed to send due to ${exception.message}")})
    }

}

data class DomainEvent (
        val action: EventType,
        val id: String,
        val payload: Any?,
        val resourceType: ResourceType
)

enum class ResourceType {
    ORDER
}

enum class EventType {
    CREATED, UPDATED, DELETED
}
