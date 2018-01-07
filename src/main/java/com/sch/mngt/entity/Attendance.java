/*package com.sch.mngt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ATTENDANCE")
@SequenceGenerator(name = "SEQ_ATTENDANCE", sequenceName = "SEQ_ATTENDANCE", initialValue = 1)
public class Attendance implements Serializable {

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 6860138820800098448L;

	@Id
	@GeneratedValue(generator = "SEQ_ATTENDANCE", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ATTENTANCE_DATE")
	private Date attendanceDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Student student;

}
*/