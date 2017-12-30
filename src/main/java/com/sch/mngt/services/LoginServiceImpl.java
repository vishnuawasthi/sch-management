package com.sch.mngt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sch.mngt.dto.RegistrationResponseDTO;
import com.sch.mngt.dto.UserProfileDTO;
import com.sch.mngt.entity.User;
import com.sch.mngt.repository.UserRepository;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserProfileDTO getUserById(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistrationResponseDTO save(UserProfileDTO userProfileDTO) {
		RegistrationResponseDTO dto = new RegistrationResponseDTO();
		User user = new User();
		user.setFirstName(userProfileDTO.getFirstName());
		user.setMiddleName(userProfileDTO.getMiddleName());
		user.setLastName(userProfileDTO.getLastName());
		user.setEmail(userProfileDTO.getEmail());
		user.setContactNumber(userProfileDTO.getContactNumber());
		user.setUsername(userProfileDTO.getUsername());
		user.setPassword(userProfileDTO.getPassword());
		user.setSchoolRegNumber(userProfileDTO.getSchoolRegNumber());
		
		User saveUser = null;
		try {
			saveUser = userRepository.save(user);
			dto.setRegisteredId(saveUser.getId());
			dto.setSuccess(true);
		} catch (Exception e) {
			throw e;
		}
		return dto;
	}

}
