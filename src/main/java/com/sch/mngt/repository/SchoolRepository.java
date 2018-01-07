package com.sch.mngt.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sch.mngt.entity.School;

public interface SchoolRepository  extends PagingAndSortingRepository<School, Long>{

}
