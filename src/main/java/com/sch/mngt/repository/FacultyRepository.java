package com.sch.mngt.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sch.mngt.entity.Faculty;

public interface FacultyRepository extends PagingAndSortingRepository<Faculty, Long>  {

}
