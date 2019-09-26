package com.github.ricardobaumann.reportservice

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.stereotype.Component


@Configuration
class KafkaProducerConfig {

    @Bean
    fun kafkaAdmin(kafkaSettings: KafkaSettings): KafkaAdmin = KafkaAdmin(mapOf(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaSettings.bootstrapAddress))

    @Bean
    fun topic1(): NewTopic = NewTopic("customer", 1, 1)

    @Bean
    fun producerFactory(kafkaSettings: KafkaSettings): ProducerFactory<String, String> = DefaultKafkaProducerFactory(
            mapOf(
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaSettings.bootstrapAddress,
                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java
            )
    )

    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String,String>) = KafkaTemplate(producerFactory)

}

@Component
@ConfigurationProperties(prefix = "kafka")
data class KafkaSettings(
        var bootstrapAddress: String? = null
)
