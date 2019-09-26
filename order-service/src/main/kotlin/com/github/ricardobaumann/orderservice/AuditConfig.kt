package com.github.ricardobaumann.orderservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class AuditConfig {

    @Bean
    fun auditorProvider(): AuditorAware<String> = AuditorAware {
        Optional.of("user")
    }

}
