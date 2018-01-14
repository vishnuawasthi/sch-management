package com.sch.mngt.services;

import com.sch.mngt.dto.AttendanceDTO;
import com.sch.mngt.dto.StudentDetailsDTO;
import com.sch.mngt.exception.RecordNotFoundException;

public interface StudentService {

	void saveAttendance(AttendanceDTO attendanceDTO);

	Long createStudentProfile(StudentDetailsDTO studentDetailsDTO) throws RecordNotFoundException;

	StudentDetailsDTO updateStudentProfile(StudentDetailsDTO studentDetailsDTO) throws RecordNotFoundException;

	StudentDetailsDTO loadStudentById(Long id) throws RecordNotFoundException;
	
	StudentDetailsDTO loadStudentByUsername(String username) throws RecordNotFoundException;
}
