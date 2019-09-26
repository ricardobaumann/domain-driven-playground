package com.github.ricardobaumann.reportservice

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@EnableKafka
@Configuration
class KafkaConsumerConfig {

    @Bean
    fun consumerFactory(kafkaSettings: KafkaSettings): ConsumerFactory<String, String>  = DefaultKafkaConsumerFactory(mapOf(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaSettings.bootstrapAddress,
                ConsumerConfig.GROUP_ID_CONFIG to "report-service",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java
        ))

    @Bean
    fun kafkaListenerContainerFactory(consumerFactory: ConsumerFactory<String, String>): ConcurrentKafkaListenerContainerFactory<String, String> {
        return ConcurrentKafkaListenerContainerFactory<String, String>().apply {
            this.consumerFactory = consumerFactory
        }
    }
}
