package com.sch.mngt.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public class SchoolDTO {

	private Long id;

	@NotEmpty(message = "schoolName should not be empty or null")
	private String schoolName;

	@NotEmpty(message = "registrationNumber should not be empty or null")
	private String registrationNumber;

	@NotEmpty(message = "addressLine1 should not be empty or null")
	private String addressLine1;

	@NotEmpty(message = "addressLine2 should not be empty or null")
	private String addressLine2;

	@NotEmpty(message = "zipcode should not be empty or null")
	private String zipcode;

	@NotEmpty(message = "city should not be empty or null")
	private String city;

	@NotEmpty(message = "state should not be empty or null")
	private String state;

	@NotEmpty(message = "country should not be empty or null")
	private String country;

}
