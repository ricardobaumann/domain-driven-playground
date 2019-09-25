package com.github.ricardobaumann.customerservice

import org.slf4j.LoggerFactory
import org.springframework.data.rest.core.annotation.*
import org.springframework.stereotype.Component

@Component
@RepositoryEventHandler
class CustomerEventHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    @HandleAfterSave
    fun handleAfterSave(customer: Customer) {
        logger.info("Saving/Updating $customer")
    }

    @HandleAfterCreate
    fun handleAfterCreate(customer: Customer) {
        logger.info("Creating $customer")
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    fun handleBeforeSave(customer: Customer) {
        logger.info("Before $customer")
    }
}
