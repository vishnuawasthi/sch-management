package com.sch.mngt.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDTO {

	private Long id;
	
	@NotEmpty(message = "schoolName should not be empty or null")
	private String schoolName;
	
	@NotEmpty(message = "registrationNumber should not be empty or null")
	private String registrationNumber;

	private String adddress;

}
