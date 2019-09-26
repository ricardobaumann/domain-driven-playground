package com.github.ricardobaumann.orderservice

import org.slf4j.LoggerFactory
import org.springframework.data.rest.core.annotation.HandleAfterCreate
import org.springframework.data.rest.core.annotation.HandleAfterDelete
import org.springframework.data.rest.core.annotation.HandleAfterSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component

@Component
@RepositoryEventHandler
class RepoEventHandler(private val domainEventRepo: DomainEventRepo) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @HandleAfterSave
    fun handleAfterSaveCustomer(customerOrder: CustomerOrder) {
        logger.info("Saving/Updating $customerOrder")
        domainEventRepo.updated(customerOrder.id, ResourceType.ORDER,customerOrder)
    }

    @HandleAfterCreate
    fun handleAfterCreateCustomer(customerOrder: CustomerOrder) {
        logger.info("Creating $customerOrder")
        domainEventRepo.created(customerOrder.id, ResourceType.ORDER, customerOrder)
    }

    @HandleAfterDelete
    fun handleAfterDeleteCustomer(customerOrder: CustomerOrder) {
        domainEventRepo.deleted(customerOrder.id, ResourceType.ORDER)
    }
}
