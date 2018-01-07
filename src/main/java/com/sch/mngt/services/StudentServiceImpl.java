package com.sch.mngt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sch.mngt.dto.AttendanceDTO;
import com.sch.mngt.entity.MonthlyAttendance;
import com.sch.mngt.repository.MonthlyAttendanceRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private MonthlyAttendanceRepository monthlyAttendanceRepository;

	@Override
	public void saveAttendance(AttendanceDTO attendanceDTO) {
		// TODO Auto-generated method stub
		System.out.println("saveAttendance() - start");
		MonthlyAttendance entity = monthlyAttendanceRepository.loadAttendance(attendanceDTO.getMonthName(),
				attendanceDTO.getStudentId());
		if (null != entity) {
			// Logic to update the entity
		}
		System.out.println("saveAttendance() - end");
	}

}
