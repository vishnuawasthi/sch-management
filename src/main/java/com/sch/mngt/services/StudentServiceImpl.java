
package com.sch.mngt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sch.mngt.constant.GradeType;
import com.sch.mngt.dto.AttendanceDTO;
import com.sch.mngt.dto.StudentDetailsDTO;
import com.sch.mngt.entity.MonthlyAttendance;
import com.sch.mngt.entity.School;
import com.sch.mngt.entity.Student;
import com.sch.mngt.exception.RecordNotFoundException;
import com.sch.mngt.repository.ClassRepository;
import com.sch.mngt.repository.MonthlyAttendanceRepository;
import com.sch.mngt.repository.SchoolRepository;
import com.sch.mngt.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private MonthlyAttendanceRepository monthlyAttendanceRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private ClassRepository classRepository;

	@Override
	public void saveAttendance(AttendanceDTO attendanceDTO) {
		System.out.println("saveAttendance() - start");
		MonthlyAttendance entity = monthlyAttendanceRepository.loadAttendance(attendanceDTO.getMonthName(),
				attendanceDTO.getStudentId());
		if (null != entity) {
			// Logic to update the entity

		}
		System.out.println("saveAttendance() - end");
	}

	@Override
	public Long createStudentProfile(StudentDetailsDTO studentDetailsDTO) throws RecordNotFoundException {
		System.out.println("createStudentProfile() - start");

		Student entity = new Student();
		entity.setFirstName(studentDetailsDTO.getFirstName());
		entity.setLastName(studentDetailsDTO.getLastName());
		entity.setMiddleName(studentDetailsDTO.getMiddleName());
		entity.setEmail(studentDetailsDTO.getEmail());
		entity.setUsername(studentDetailsDTO.getUsername());
		entity.setPassword(studentDetailsDTO.getPassword());

		String grade = GradeType.valueOf(studentDetailsDTO.getGrade()).toString();

		School school = schoolRepository.findOne(studentDetailsDTO.getSchoolId());

		if (null == school) {
			throw new RecordNotFoundException("School not found");
		}

		com.sch.mngt.entity.Class classDetails = classRepository.loadClassByGrade(grade);

		if (null == classDetails) {
			throw new RecordNotFoundException("Class not found with provided grade");
		}
		entity.setSchool(school);
		entity.setClassDetail(classDetails);
		Student savedEntity = studentRepository.save(entity);
		System.out.println("createStudentProfile() - end");
		return savedEntity.getId();
	}

	@Override
	public StudentDetailsDTO updateStudentProfile(StudentDetailsDTO studentDetailsDTO) throws RecordNotFoundException {
		System.out.println("updateStudentProfile() - start");

		Student entity = studentRepository.findOne(studentDetailsDTO.getId());

		if (null == entity) {
			throw new RecordNotFoundException("Student not found");
		}

		if (!StringUtils.isEmpty(studentDetailsDTO.getFirstName())) {
			entity.setFirstName(studentDetailsDTO.getFirstName());
		}
		if (!StringUtils.isEmpty(studentDetailsDTO.getLastName())) {
			entity.setLastName(studentDetailsDTO.getLastName());
		}

		if (!StringUtils.isEmpty(studentDetailsDTO.getMiddleName())) {
			entity.setMiddleName(studentDetailsDTO.getMiddleName());
		}
		if (!StringUtils.isEmpty(studentDetailsDTO.getEmail())) {
			entity.setEmail(studentDetailsDTO.getEmail());
		}
		if (!StringUtils.isEmpty(studentDetailsDTO.getContactNumber())) {
			entity.setContactNumber(studentDetailsDTO.getContactNumber());
		}

		Student savedEntity = studentRepository.save(entity);
		
		StudentDetailsDTO responseDTO = populateDetailsDTO(savedEntity);

		System.out.println("updateStudentProfile() - end");
		return responseDTO;
	}

	@Override
	public StudentDetailsDTO loadStudentById(Long id) throws RecordNotFoundException {
		System.out.println("loadStudentById() - start");
		Student entity = studentRepository.findOne(id);
		StudentDetailsDTO dto = null;
		if (null != entity) {
			dto = populateDetailsDTO(entity);
		}
		System.out.println("loadStudentById() - end");
		return dto;
	}

	private StudentDetailsDTO populateDetailsDTO(Student entity) {
		System.out.println("populateDetailsDTO() - start");
		StudentDetailsDTO dto = new StudentDetailsDTO();

		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setMiddleName(entity.getMiddleName());
		dto.setEmail(entity.getEmail());
		dto.setContactNumber(entity.getContactNumber());
		dto.setUsername(entity.getUsername());

		com.sch.mngt.entity.Class clazz = entity.getClassDetail();

		if (clazz != null) {
			dto.setGrade(clazz.getGrade().name());
		}
		System.out.println("populateDetailsDTO() - end");
		return dto;
	}

	@Override
	public StudentDetailsDTO loadStudentByUsername(String username) throws RecordNotFoundException {
		System.out.println("loadStudentByUsername() - start");
		Student entity = studentRepository.loadUserByUsername(username);

		if (null == entity) {
			throw new RecordNotFoundException("Student not found");
		}

		StudentDetailsDTO dto = populateDetailsDTO(entity);

		System.out.println("loadStudentByUsername() - end");
		return dto;
	}

}
