package com.sch.mngt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STUDENT_DETAILS")
@SequenceGenerator(name = "SEQ_STUDENT_DETAILS", sequenceName = "SEQ_STUDENT_DETAILS", initialValue = 1)
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDENT_DETAILS")
	@Column(name = "ID")
	private Long id;

	@Column(name = "ROLL_NUMBER", unique = true)
	private String rollNumber;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL")
	private String email;
	
	@OneToMany(mappedBy="student")
	private Set<MonthlyAttendance> monthlyAttendances = new HashSet<MonthlyAttendance>();

	/*@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private AttendanceSheet attendanceSheet;*/

}
