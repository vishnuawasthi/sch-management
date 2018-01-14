package com.sch.mngt.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sch.mngt.entity.AccessPermissions;

public interface AccessPermissionsRepository extends  PagingAndSortingRepository<AccessPermissions, Long> {

}
