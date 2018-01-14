package com.sch.mngt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sch.mngt.entity.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
	
	@Query("FROM Student student WHERE  student.username=:username")
	Student loadUserByUsername(@Param("username") String username);

}
