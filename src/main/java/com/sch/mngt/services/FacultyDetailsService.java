package com.sch.mngt.services;

import java.util.List;

import com.sch.mngt.dto.FacultyDetailsDTO;
import com.sch.mngt.exception.RecordNotFoundException;

public interface FacultyDetailsService {

	Long save(FacultyDetailsDTO facultyDetailsDTO);

	void update(FacultyDetailsDTO facultyDetailsDTO) throws RecordNotFoundException;

	void delete(Long id) throws RecordNotFoundException;
	
	List<FacultyDetailsDTO> getAll();

}
