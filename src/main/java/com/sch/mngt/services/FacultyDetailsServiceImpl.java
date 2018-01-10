package com.sch.mngt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sch.mngt.dto.FacultyDetailsDTO;
import com.sch.mngt.entity.Faculty;
import com.sch.mngt.exception.RecordNotFoundException;
import com.sch.mngt.repository.FacultyRepository;

@Service
public class FacultyDetailsServiceImpl implements FacultyDetailsService {

	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public Long save(FacultyDetailsDTO facultyDetailsDTO) {
		System.out.println("save() - start");
		try {
			Faculty entity = new Faculty();
			entity.setFacultyName(facultyDetailsDTO.getFacultyName());
			entity.setContactNumer(facultyDetailsDTO.getContactNumer());
			entity.setEmail(facultyDetailsDTO.getEmail());
			Faculty savedFaculty = facultyRepository.save(entity);

			if (null != savedFaculty) {
				System.out.println("save() - end");
				return savedFaculty.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void update(FacultyDetailsDTO facultyDetailsDTO) throws RecordNotFoundException {
		System.out.println("update() - start");
		Faculty faculty = facultyRepository.findOne(facultyDetailsDTO.getId());

		if (null != faculty) {

			if (!StringUtils.isEmpty(facultyDetailsDTO.getContactNumer())) {
				faculty.setContactNumer(facultyDetailsDTO.getContactNumer());
			}
			if (!StringUtils.isEmpty(facultyDetailsDTO.getEmail())) {
				faculty.setEmail(facultyDetailsDTO.getEmail());
			}

			if (!StringUtils.isEmpty(facultyDetailsDTO.getFacultyName())) {
				faculty.setFacultyName(facultyDetailsDTO.getFacultyName());
			}

			facultyRepository.save(faculty);

		} else {
			throw new RecordNotFoundException("No such record exist with id=" + facultyDetailsDTO.getId());
		}

		System.out.println("update() - end");
	}

	@Override
	public void delete(Long id) throws RecordNotFoundException {
		System.out.println("delete() - start");
		Faculty faculty = facultyRepository.findOne(id);

		if (null != faculty) {
			facultyRepository.delete(faculty);
		} else {
			throw new RecordNotFoundException("No such record exist with id=" + id);
		}
		System.out.println("delete() - end");

	}

	@Override
	public List<FacultyDetailsDTO> getAll() {
		System.out.println("getAll() - start");
		List<FacultyDetailsDTO> detailsList = new ArrayList<FacultyDetailsDTO>();
		
		List<Faculty> facultyData = (List<Faculty>) facultyRepository.findAll();

		if (!CollectionUtils.isEmpty(facultyData)) {
			for (Faculty entity : facultyData) {

				FacultyDetailsDTO dto = new FacultyDetailsDTO();
				
				dto.setContactNumer(entity.getContactNumer());
				dto.setFacultyName(entity.getFacultyName());
				dto.setEmail(entity.getEmail());
				dto.setClasses(entity.getClasses());
				detailsList.add(dto);

			}
		}
		System.out.println("getAll() - end");
		return detailsList;
	}

}
