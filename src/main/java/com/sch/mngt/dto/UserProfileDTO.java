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
@JsonInclude( Include.NON_NULL)
public class UserProfileDTO {

	private Long id;

	@NotEmpty(message = "Username should not be empty or null")
	private String username;

	@NotEmpty(message = "Password should not be empty or null")
	private String password;

	@NotEmpty(message = "FirstName should not be empty or null")
	private String firstName;

	@NotEmpty(message = "LastName should not be empty or null")
	private String lastName;

	private String middleName;

	@NotEmpty(message = "Email should not be empty or null")
	private String email;

	private String contactNumber;

	@NotEmpty(message = "SchoolRegNumber should not be empty or null")
	private String schoolRegNumber;

	@NotEmpty(message = "EmployeeId should not be empty or null")
	private String employeeId;

}
