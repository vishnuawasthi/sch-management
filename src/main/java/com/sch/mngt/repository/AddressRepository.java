package com.sch.mngt.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sch.mngt.entity.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

}
