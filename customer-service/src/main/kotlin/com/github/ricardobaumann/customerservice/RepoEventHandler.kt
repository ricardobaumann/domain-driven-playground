package com.github.ricardobaumann.customerservice

import org.slf4j.LoggerFactory
import org.springframework.data.rest.core.annotation.HandleAfterCreate
import org.springframework.data.rest.core.annotation.HandleAfterSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component

@Component
@RepositoryEventHandler
class RepoEventHandler(private val domainEventRepo: DomainEventRepo) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @HandleAfterSave
    fun handleAfterSaveCustomer(customer: Customer) {
        logger.info("Saving/Updating $customer")
        domainEventRepo.updated(customer.id, ResourceType.CUSTOMER,customer)
    }

    @HandleAfterCreate
    fun handleAfterCreateCustomer(customer: Customer) {
        logger.info("Creating $customer")
        domainEventRepo.created(customer.id, ResourceType.CUSTOMER, customer)
    }

    @HandleAfterSave
    fun handleAfterSaveAddress(address: Address){
        logger.info("Saving/updating $address")
        domainEventRepo.updated(address.id, ResourceType.ADDRESS, address)
    }

    @HandleAfterCreate
    fun handleAfterCreateAddress(address: Address) {
        logger.info("Creating $address")
        domainEventRepo.created(address.id, ResourceType.ADDRESS, address)
    }
}
