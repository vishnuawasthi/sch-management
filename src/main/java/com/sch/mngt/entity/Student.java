package com.sch.mngt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
//@ToString
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

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;
	
	@Column(name = "USER_NAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	/*@Column(name="GRADE")
	@Enumerated
	private GradeType grade;*/
	
	@OneToMany(mappedBy="student")
	private Set<MonthlyAttendance> monthlyAttendances = new HashSet<MonthlyAttendance>();
	
	@ManyToOne
	@JoinColumn(name="CLASS_SEQNUM")
	private com.sch.mngt.entity.Class classDetail;
	
	@ManyToOne
	@JoinColumn(name="SCHOOL_SEQNUM")
	private School school ;

	/*
	 * @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private AttendanceSheet attendanceSheet;*/

}
