package com.sch.mngt.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.sch.mngt.dto.RegistrationResponseDTO;
import com.sch.mngt.dto.UserProfileDTO;
import com.sch.mngt.exception.RecordNotFoundException;

public interface LoginService {
	
	RegistrationResponseDTO save (UserProfileDTO userProfileDTO) throws RecordNotFoundException;
	
	UserProfileDTO getUserById(String username) throws RecordNotFoundException;
	
	void updateApiKey(UserDetails userDetails,String apiKey);
	
	void removeApiKey(String username);
	
	

}
