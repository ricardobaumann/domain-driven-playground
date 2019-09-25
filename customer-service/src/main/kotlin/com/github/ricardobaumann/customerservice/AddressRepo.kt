package com.github.ricardobaumann.customerservice

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface AddressRepo : PagingAndSortingRepository<Address, String>
