package com.github.ricardobaumann.customerservice

import org.springframework.data.rest.core.annotation.RestResource
import java.util.*
import javax.persistence.*

@Entity
data class Address (
    @Id var id: String = UUID.randomUUID().toString(),
    var description: String,
    var enabled: Boolean = true,
    @RestResource @ManyToOne @JoinColumn(updatable = false, nullable = false) var customer: Customer? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Address

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    @PreUpdate
    @PrePersist
    fun prePersist() {
    }

    override fun toString(): String {
        return "Address(id=$id, description='$description', enabled=$enabled)"
    }


}
