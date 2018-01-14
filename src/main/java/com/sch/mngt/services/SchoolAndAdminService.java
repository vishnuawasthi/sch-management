package com.sch.mngt.services;

import java.util.List;

import com.sch.mngt.dto.AccessPermissionsDTO;
import com.sch.mngt.dto.ClassDetailsDTO;
import com.sch.mngt.dto.SchoolAdminDTO;
import com.sch.mngt.dto.SchoolDTO;
import com.sch.mngt.dto.UserRoleDTO;
import com.sch.mngt.exception.RecordNotFoundException;

public interface SchoolAndAdminService {

	Long createSchool(SchoolDTO school);

	SchoolDTO getSchool(Long id) throws RecordNotFoundException;

	List<SchoolDTO> getAll();

	//Long createSchoolAdmin(SchoolAdminDTO schoolAdminDTO) throws RecordNotFoundException;

	Long createClass(ClassDetailsDTO classDetailsDTO) throws RecordNotFoundException;

	ClassDetailsDTO getClassDetails(Long id)  throws RecordNotFoundException;

	ClassDetailsDTO updateClassDetails(ClassDetailsDTO classDetailsDTO )  throws RecordNotFoundException;
	
	void updateAccessFunction(AccessPermissionsDTO accessPermissionsDTO)  throws RecordNotFoundException;
	
	SchoolAdminDTO getAdminDetails (Long id)  throws RecordNotFoundException;
	
	Long  createRole(UserRoleDTO userRoleDTO) throws RecordNotFoundException;
	
	
	
	

}
