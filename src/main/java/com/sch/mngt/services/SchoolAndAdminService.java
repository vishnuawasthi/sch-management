package com.sch.mngt.services;

import java.util.List;

import com.sch.mngt.dto.SchoolAdminDTO;
import com.sch.mngt.dto.SchoolDTO;
import com.sch.mngt.exception.RecordNotFoundException;

public interface SchoolAndAdminService  {

	Long createSchool(SchoolDTO school);

	SchoolDTO getSchool(Long id) throws RecordNotFoundException;

	List<SchoolDTO> getAll();
	
	Long createSchoolAdmin(SchoolAdminDTO schoolAdminDTO)  throws RecordNotFoundException;

}
