/**
 * 
 *//*
package com.sch.mngt.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

*//**
 * @author Vishnu Awasthi
 *
 *//*

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ATTENTANCE_SHEET")

public class AttendanceSheet implements Serializable {

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1254695926394509735L;

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "student") )
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	// attendanceSheet
	@OneToMany(mappedBy = "attendanceSheet")
	private Set<MonthDetails> months = new HashSet<MonthDetails>();

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Student student;

}
*/