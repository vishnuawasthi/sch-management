package com.sch.mngt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sch.mngt.constant.GradeType;

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
@Table(name = "CLASS_DETAILS")
@SequenceGenerator(name = "SEQ_CLASS_DETAILS", sequenceName = "SEQ_CLASS_DETAILS", initialValue = 1)
public class Class {

	@Id
	@GeneratedValue(generator = "SEQ_CLASS_DETAILS", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "GRADE", unique = true, nullable = false)
	@Enumerated(EnumType.STRING)
	private GradeType grade;

	@Column(name = "DESCRIPTION")
	private String description;

	/*
	 * @OneToMany(mappedBy = "clazz") private Set<Student> students = new
	 * HashSet<Student>();
	 */

	@ManyToOne
	@JoinColumn(name = "FACULTY_DETAILS_SEQNUM")
	private Faculty faculty;

	/*@ManyToOne
	@JoinColumn(name = "FACULTY_DETAILS_SEQNUM")
	private AttendanceSheet attendanceSheet;*/

}
