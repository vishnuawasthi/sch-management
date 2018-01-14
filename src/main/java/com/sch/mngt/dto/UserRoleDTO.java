package com.sch.mngt.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserRoleDTO {

	private Long id;

	@NotEmpty(message="roleName should not be empty or null")
	private String roleName;

	private String description;
	
	@NotEmpty(message="schoolId should not be empty or null")
	private Long schoolId;
}
