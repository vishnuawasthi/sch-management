package com.sch.mngt.dto;




import javax.validation.constraints.NotNull;

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

	@NotEmpty(message = "username should not be empty or null")
	private String username;

	@NotEmpty(message = "password should not be empty or null")
	private String password;

	@NotEmpty(message = "firstName should not be empty or null")
	private String firstName;

	@NotEmpty(message = "lastName should not be empty or null")
	private String lastName;

	private String middleName;

	@NotEmpty(message = "email should not be empty or null")
	private String email;

	private String contactNumber;
	
	private String apiKey ;
	
	//@NotEmpty(message = "schoolId should not be empty or null")
	@NotNull(message="schoolId should not be empty or null")
	private Long schoolId;
	
	@NotEmpty(message = "roleName should not be empty or null")
	private String roleName;
	

}
