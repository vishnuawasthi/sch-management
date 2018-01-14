package com.sch.mngt.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sch.mngt.constant.GradeType;
import com.sch.mngt.constant.UserType;
import com.sch.mngt.dto.AccessPermissionsDTO;
import com.sch.mngt.dto.ClassDetailsDTO;
import com.sch.mngt.dto.LiabilitiesDetailDTO;
import com.sch.mngt.dto.SchoolAdminDTO;
import com.sch.mngt.dto.SchoolDTO;
import com.sch.mngt.dto.UserRoleDTO;
import com.sch.mngt.entity.AccessPermissions;
import com.sch.mngt.entity.Address;
import com.sch.mngt.entity.School;
import com.sch.mngt.entity.User;
import com.sch.mngt.entity.UserRole;
import com.sch.mngt.exception.RecordNotFoundException;
import com.sch.mngt.repository.AccessPermissionsRepository;
import com.sch.mngt.repository.AddressRepository;
import com.sch.mngt.repository.ClassRepository;
import com.sch.mngt.repository.SchoolRepository;
import com.sch.mngt.repository.UserRepository;
import com.sch.mngt.repository.UserRoleRepository;

/**
 * 
 * @author Vishnu Awasthi on 1/7/2018
 *
 */
@Service
public class SchoolAndAdminServiceImpl implements SchoolAndAdminService {

	@Autowired
	private SchoolRepository schoolRepository;

	/*
	 * @Autowired private SchoolAdminRepository schoolAdminRepository;
	 */
	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccessPermissionsRepository accessPermissionsRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public Long createSchool(SchoolDTO schoolDTO) {
		System.out.println("createSchool() - start");
		School entity = new School();
		entity.setSchoolName(schoolDTO.getSchoolName());
		entity.setRegistrationNumber(schoolDTO.getRegistrationNumber());
		entity.setCreatedDate(new Date());

		Address addressEntity = new Address();
		addressEntity.setAddressLine1(schoolDTO.getAddressLine1());
		addressEntity.setAddressLine2(schoolDTO.getAddressLine2());
		addressEntity.setCity(schoolDTO.getCity());
		addressEntity.setState(schoolDTO.getState());
		addressEntity.setCountry(schoolDTO.getCity());
		addressEntity.setZipcode(schoolDTO.getZipcode());

		entity.setAddress(addressEntity);

		School savedEntity = schoolRepository.save(entity);

		if (null != savedEntity) {
			System.out.println("createSchool() - end");
			return savedEntity.getId();
		}
		return null;
	}

	@Override
	public SchoolDTO getSchool(Long id) throws RecordNotFoundException {
		System.out.println("getSchool() - start");

		School entity = schoolRepository.findOne(id);
		if (null != entity) {
			SchoolDTO dto = new SchoolDTO();
			dto.setId(entity.getId());
			dto.setRegistrationNumber(entity.getRegistrationNumber());
			dto.setSchoolName(entity.getSchoolName());
			System.out.println("getSchool() - end");
			return dto;
		}
		throw new RecordNotFoundException("School not found with given id");
	}

	@Override
	public List<SchoolDTO> getAll() {
		System.out.println("getAll() - start");
		List<SchoolDTO> schoolsList = new ArrayList<SchoolDTO>();
		Iterable<School> schools = schoolRepository.findAll();
		for (School entity : schools) {
			SchoolDTO dto = new SchoolDTO();
			dto.setId(entity.getId());
			dto.setRegistrationNumber(entity.getRegistrationNumber());
			dto.setSchoolName(entity.getSchoolName());
			schoolsList.add(dto);

		}
		System.out.println("getAll() - end");
		return schoolsList;
	}

	/*
	 * @Override public Long createSchoolAdmin(SchoolAdminDTO schoolAdminDTO)
	 * throws RecordNotFoundException { School school =
	 * schoolRepository.findOne(schoolAdminDTO.getSchoolId()); if (null ==
	 * school) { throw new RecordNotFoundException("School not found"); }
	 * SchoolAdmin entity = populateSchoolAdmin(schoolAdminDTO);
	 * entity.setSchool(school); SchoolAdmin savedAdmin =
	 * schoolAdminRepository.save(entity); return savedAdmin.getId(); }
	 * 
	 * private SchoolAdmin populateSchoolAdmin(SchoolAdminDTO schoolAdminDTO) {
	 * SchoolAdmin entity = new SchoolAdmin();
	 * entity.setFirstName(schoolAdminDTO.getFirstName());
	 * entity.setLastName(schoolAdminDTO.getLastName());
	 * entity.setUsername(schoolAdminDTO.getUsername());
	 * entity.setPassword(schoolAdminDTO.getPassword());
	 * entity.setEmail(schoolAdminDTO.getEmail());
	 * entity.setContactNumber(schoolAdminDTO.getContactNumber()); return
	 * entity; }
	 */

	@Override
	public Long createClass(ClassDetailsDTO classDetailsDTO) throws RecordNotFoundException {
		System.out.println("createClass() - start");
		School school = schoolRepository.findOne(classDetailsDTO.getSchoolId());
		if (null == school) {
			throw new RecordNotFoundException("School not found");
		}

		com.sch.mngt.entity.Class entity = new com.sch.mngt.entity.Class();
		entity.setGrade(GradeType.valueOf(classDetailsDTO.getGrade()));
		entity.setSchool(school);
		entity.setDescription(classDetailsDTO.getDescription());

		com.sch.mngt.entity.Class savedEntity = classRepository.save(entity);
		Long id = null;
		if (null != savedEntity) {
			id = savedEntity.getId();
		}
		System.out.println("createClass() - end");
		return id;
	}

	@Override
	public ClassDetailsDTO getClassDetails(Long id) throws RecordNotFoundException {
		System.out.println("getClassDetails() - start");
		com.sch.mngt.entity.Class entity = classRepository.findOne(id);
		if (null != entity) {
			ClassDetailsDTO classDetailsDTO = new ClassDetailsDTO();
			classDetailsDTO.setDescription(entity.getDescription());
			classDetailsDTO.setGrade(entity.getGrade().toString());
			classDetailsDTO.setSchoolId(entity.getSchool().getId());
			System.out.println("getClassDetails() - end");
			return classDetailsDTO;
		}
		throw new RecordNotFoundException("Class not found");
	}

	@Override
	public ClassDetailsDTO updateClassDetails(ClassDetailsDTO classDetailsDTO) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccessFunction(AccessPermissionsDTO accessPermissionsDTO) throws RecordNotFoundException {
		System.out.println("updateAccessFunction() - start");

		User user = userRepository.findOne(accessPermissionsDTO.getUserId());

		if (null == user) {
			throw new RecordNotFoundException("User not found");
		}
		AccessPermissions permissions = user.getAccessPermissions();

		if (null == permissions) {
			permissions = new AccessPermissions();
		}

		permissions.setUser(user);

		user.setAccessPermissions(permissions);
		User updatedUser = userRepository.save(user);

		if (!CollectionUtils.isEmpty(accessPermissionsDTO.getClasses())) {
			for (Long classId : accessPermissionsDTO.getClasses()) {
				com.sch.mngt.entity.Class classDetails = classRepository.findOne(classId);
				if (null == classDetails) {
					throw new RecordNotFoundException("Class not found with ID = " + classId);
				}
				classDetails.setAccessPermissions(updatedUser.getAccessPermissions());
				classRepository.save(classDetails);
			}
		}
		System.out.println("updateAccessFunction() - end");

	}

	@Override
	public SchoolAdminDTO getAdminDetails(Long id) throws RecordNotFoundException {
		System.out.println("getAdminDetails() - start");

		User user = userRepository.findOne(id);

		if (null == user) {
			throw new RecordNotFoundException("User not found");
		}

		SchoolAdminDTO schoolAdminDTO = new SchoolAdminDTO();
		// Personal details
		schoolAdminDTO.setFirstName(user.getFirstName());
		schoolAdminDTO.setLastName(user.getLastName());
		schoolAdminDTO.setMiddleName(user.getMiddleName());
		schoolAdminDTO.setId(user.getId());
		Set<String> roles = new HashSet<String>();

		for (UserRole role : user.getRoles()) {
			roles.add(role.getRoleName().name());
		}
		schoolAdminDTO.setRoleName(roles);
		// School Details
		School school = user.getSchool();

		SchoolDTO schoolDetails = new SchoolDTO();
		if (null != school) {
			schoolDetails.setId(school.getId());
			schoolDetails.setSchoolName(school.getSchoolName());
		}

		schoolAdminDTO.setSchoolDetails(schoolDetails);

		AccessPermissions accessPermissions = user.getAccessPermissions();

		LiabilitiesDetailDTO liabilities = new LiabilitiesDetailDTO();

		Set<com.sch.mngt.dto.ClassDetailsDTO> assignedClasses = new HashSet<com.sch.mngt.dto.ClassDetailsDTO>();

		for (com.sch.mngt.entity.Class clazz : accessPermissions.getClasses()) {
			com.sch.mngt.dto.ClassDetailsDTO classDetail = new com.sch.mngt.dto.ClassDetailsDTO();
			classDetail.setGrade(clazz.getGrade().name());
			classDetail.setId(clazz.getId());
			classDetail.setDescription(clazz.getDescription());
			assignedClasses.add(classDetail);
		}

		liabilities.setAssignedClasses(assignedClasses);
		schoolAdminDTO.setLiabilities(liabilities);
		System.out.println("getAdminDetails() - end");
		return schoolAdminDTO;
	}

	@Override
	public Long createRole(UserRoleDTO userRoleDTO) throws RecordNotFoundException {
		System.out.println("createRole() - end");
		School school = schoolRepository.findOne(userRoleDTO.getSchoolId());

		if (null == school) {
			throw new RecordNotFoundException("School not found with ID = " + userRoleDTO.getSchoolId());
		}

		UserRole entity = new UserRole();
		entity.setDescription(userRoleDTO.getDescription());
		entity.setRoleName(UserType.valueOf(userRoleDTO.getRoleName()));
		entity.setSchool(school);
		UserRole savedEntity = userRoleRepository.save(entity);

		System.out.println("createRole() - end");
		return savedEntity.getId();
	}

}
