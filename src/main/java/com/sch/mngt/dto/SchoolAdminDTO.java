package com.sch.mngt.dto;

import java.util.Set;

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
public class SchoolAdminDTO {

	private Long id;
	private String firstName;
	private String lastName;
	
	private String middleName;
	// private String username;
	// private String password;
	private String email;
	private String contactNumber;
	private Set<String> roleName;
	private SchoolDTO schoolDetails;
	private LiabilitiesDetailDTO liabilities;
}
