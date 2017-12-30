package com.sch.mngt.services;

import com.sch.mngt.dto.RegistrationResponseDTO;
import com.sch.mngt.dto.UserProfileDTO;

public interface LoginService {
	
	RegistrationResponseDTO save (UserProfileDTO userProfileDTO);
	UserProfileDTO getUserById(String username);
	
	
	

}
