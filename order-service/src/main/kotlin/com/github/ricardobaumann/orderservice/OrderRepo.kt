package com.github.ricardobaumann.orderservice

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface OrderRepo : PagingAndSortingRepository<CustomerOrder, String>
