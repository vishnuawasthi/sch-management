package com.sch.mngt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sch.mngt.constant.GradeType;
import com.sch.mngt.dto.ClassDetailsDTO;
import com.sch.mngt.dto.SchoolDTO;
import com.sch.mngt.entity.School;
import com.sch.mngt.exception.RecordNotFoundException;
import com.sch.mngt.repository.ClassRepository;
import com.sch.mngt.repository.SchoolRepository;

/**
 * 
 * @author Vishnu Awasthi on 1/7/2018
 *
 */
@Service
public class SchoolAndAdminServiceImpl implements SchoolAndAdminService {

	@Autowired
	private SchoolRepository schoolRepository;

	/*@Autowired
	private SchoolAdminRepository schoolAdminRepository;*/

	@Autowired
	private ClassRepository classRepository;

	@Override
	public Long createSchool(SchoolDTO school) {
		System.out.println("createSchool() - start");

		School entity = new School();
		entity.setSchoolName(school.getSchoolName());
		entity.setAdddress(school.getAdddress());
		entity.setRegistrationNumber(school.getRegistrationNumber());
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
			dto.setAdddress(entity.getAdddress());
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
			dto.setAdddress(entity.getAdddress());
			dto.setId(entity.getId());
			dto.setRegistrationNumber(entity.getRegistrationNumber());
			dto.setSchoolName(entity.getSchoolName());
			schoolsList.add(dto);

		}
		System.out.println("getAll() - end");
		return schoolsList;
	}

	/*@Override
	public Long createSchoolAdmin(SchoolAdminDTO schoolAdminDTO) throws RecordNotFoundException {
		School school = schoolRepository.findOne(schoolAdminDTO.getSchoolId());
		if (null == school) {
			throw new RecordNotFoundException("School not found");
		}
		SchoolAdmin entity = populateSchoolAdmin(schoolAdminDTO);
		entity.setSchool(school);
		SchoolAdmin savedAdmin = schoolAdminRepository.save(entity);
		return savedAdmin.getId();
	}

	private SchoolAdmin populateSchoolAdmin(SchoolAdminDTO schoolAdminDTO) {
		SchoolAdmin entity = new SchoolAdmin();
		entity.setFirstName(schoolAdminDTO.getFirstName());
		entity.setLastName(schoolAdminDTO.getLastName());
		entity.setUsername(schoolAdminDTO.getUsername());
		entity.setPassword(schoolAdminDTO.getPassword());
		entity.setEmail(schoolAdminDTO.getEmail());
		entity.setContactNumber(schoolAdminDTO.getContactNumber());
		return entity;
	}*/

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

}
