package com.github.ricardobaumann.orderservice

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class CustomerOrder(
        @Id val id: String = UUID.randomUUID().toString(),
        val customer: String,
        val amount: Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CustomerOrder

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
