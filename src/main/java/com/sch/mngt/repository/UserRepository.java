package com.sch.mngt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sch.mngt.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("FROM User u WHERE u.username=:username")
	User loadUserByUsername(@Param("username") String username);
}
