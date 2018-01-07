package com.sch.mngt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sch.mngt.entity.MonthlyAttendance;

public interface MonthlyAttendanceRepository extends PagingAndSortingRepository<MonthlyAttendance, Long> {

	@Query("FROM MonthlyAttendance entity WHERE entity.monthName =:monthName AND entity.student.id =:studentId ")
	MonthlyAttendance loadAttendance(@Param("monthName") String monthName, @Param("studentId")  Long studentId);

}
