package com.sch.mngt.dto;

import java.util.List;

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
public class Response {

	
	private UserProfileDTO userProfile;

	private RegistrationResponseDTO regResponse;
	
	private LoginResponseDTO accessCredentials;
	
	private SchoolDTO schoolDetail;
	
	private List<SchoolDTO> schoolDetails;
	
	private SchoolAdminDTO schoolAdminDetail;
	
	private ClassDetailsDTO classDetail;
	

}
