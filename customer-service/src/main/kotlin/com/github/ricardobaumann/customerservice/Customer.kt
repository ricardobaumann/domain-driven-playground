package com.github.ricardobaumann.customerservice

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
data class Customer(
        @Id var id: String = UUID.randomUUID().toString(),
        var name: String,
        @CreatedDate @Column(updatable = false) var createdAt: LocalDateTime? = null,
        @LastModifiedDate var updatedAt: LocalDateTime? = null,
        @CreatedBy @Column(updatable = false) var createdBy: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Customer

        if (id != other.id) return false

        return true
    }

    @PreUpdate
    @PrePersist
    fun prePersist() {
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
