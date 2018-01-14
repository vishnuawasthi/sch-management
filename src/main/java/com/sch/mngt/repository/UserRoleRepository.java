package com.sch.mngt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sch.mngt.entity.UserRole;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Long> {

	@Query("FROM UserRole userRole WHERE userRole.roleName  =:roleName AND userRole.school.id =:schoolId")
	UserRole getRoleBySchoolIdAndName(String roleName, Long schoolId);
}
