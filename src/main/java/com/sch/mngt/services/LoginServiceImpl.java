package com.sch.mngt.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sch.mngt.dto.RegistrationResponseDTO;
import com.sch.mngt.dto.UserProfileDTO;
import com.sch.mngt.entity.School;
import com.sch.mngt.entity.User;
import com.sch.mngt.entity.UserRole;
import com.sch.mngt.exception.RecordNotFoundException;
import com.sch.mngt.repository.SchoolRepository;
import com.sch.mngt.repository.UserRepository;
import com.sch.mngt.repository.UserRoleRepository;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public UserProfileDTO getUserById(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistrationResponseDTO save(UserProfileDTO userProfileDTO) throws RecordNotFoundException {
		System.out.println("save() - start");
		RegistrationResponseDTO dto = new RegistrationResponseDTO();

		School school = schoolRepository.findOne(userProfileDTO.getSchoolId());

		if (null == school) {
			throw new RecordNotFoundException("School not found with ID = " + userProfileDTO.getSchoolId());
		}

		User user = new User();
		user.setFirstName(userProfileDTO.getFirstName());
		user.setMiddleName(userProfileDTO.getMiddleName());
		user.setLastName(userProfileDTO.getLastName());
		user.setEmail(userProfileDTO.getEmail());
		user.setContactNumber(userProfileDTO.getContactNumber());
		user.setUsername(userProfileDTO.getUsername());
		user.setPassword(userProfileDTO.getPassword());
		user.setSchool(school);

		Set<UserRole> roles = new HashSet<UserRole>();
		UserRole userRole = userRoleRepository.getRoleBySchoolIdAndName(userProfileDTO.getRoleName(),userProfileDTO.getSchoolId());
		
		if (null != userRole) {
			roles.add(userRole);
			user.setRoles(roles);
		} else {
			throw new RecordNotFoundException("UserRole not found => " + userProfileDTO.getRoleName());
		}
		User saveUser = null;
		try {
			saveUser = userRepository.save(user);
			dto.setRegisteredId(saveUser.getId());
			dto.setSuccess(true);
		} catch (Exception e) {
			throw e;
		}
		System.out.println("save() - end");
		return dto;
	}

	@Override
	public void updateApiKey(UserDetails userDetails, String apiKey) {
		System.out.println("updateApiKey() - start");
		User entity = userRepository.loadUserByUsername(userDetails.getUsername());
		if (null != entity) {
			entity.setApiKey(apiKey);
			userRepository.save(entity);
		}
		System.out.println("updateApiKey() - end");
	}

	@Override
	public void removeApiKey(String username) {
		System.out.println("removeApiKey() - start");
		User entity = userRepository.loadUserByUsername(username);
		if (null != entity) {
			entity.setApiKey("");
			userRepository.save(entity);
		}
	}

}
