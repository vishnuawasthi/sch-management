package com.sch.mngt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ClassRepository extends PagingAndSortingRepository<com.sch.mngt.entity.Class, Long> {

	@Query("FROM Class clazz WHERE  clazz.grade =:grade ")
	com.sch.mngt.entity.Class loadClassByGrade(@Param("grade") String grade);
}
