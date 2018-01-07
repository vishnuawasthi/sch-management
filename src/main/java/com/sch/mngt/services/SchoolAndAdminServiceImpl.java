package com.sch.mngt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sch.mngt.dto.SchoolAdminDTO;
import com.sch.mngt.dto.SchoolDTO;
import com.sch.mngt.entity.School;
import com.sch.mngt.entity.SchoolAdmin;
import com.sch.mngt.exception.RecordNotFoundException;
import com.sch.mngt.repository.SchoolAdminRepository;
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

	@Autowired
	private SchoolAdminRepository schoolAdminRepository;

	@Override
	public Long createSchool(SchoolDTO school) {
		System.out.println("createSchool() - start");

		School entity = new School();
		entity.setSchoolName(school.getSchoolName());
		entity.setAdddress(school.getAdddress());
		entity.setRegistratinNumber(school.getRegistrationNumber());
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
			dto.setRegistrationNumber(entity.getRegistratinNumber());
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
			dto.setRegistrationNumber(entity.getRegistratinNumber());
			dto.setSchoolName(entity.getSchoolName());
			schoolsList.add(dto);

		}
		System.out.println("getAll() - end");
		return schoolsList;
	}

	@Override
	public Long createSchoolAdmin(SchoolAdminDTO schoolAdminDTO) throws RecordNotFoundException {
		School school = schoolRepository.findOne(schoolAdminDTO.getSchoolId());
		if (null == school) {
			throw new RecordNotFoundException("School not found");
		}
		SchoolAdmin entity = populateSchoolAdmin(schoolAdminDTO);
		entity.setSchool(school);
		SchoolAdmin savedAdmin=	schoolAdminRepository.save(entity);
		return savedAdmin.getId();
	}

	private SchoolAdmin populateSchoolAdmin(SchoolAdminDTO schoolAdminDTO) {
		SchoolAdmin entity = new SchoolAdmin();
		return entity;
	}

}
