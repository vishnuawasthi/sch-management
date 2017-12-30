package com.sch.mngt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "APP_LOGS")
@SequenceGenerator(name = "SEQ_APP_LOGS", sequenceName = "SEQ_APP_LOGS", initialValue = 1)
public class AppLogs {

	@Id
	@GeneratedValue(generator = "SEQ_APP_LOGS", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "REQUEST_BODY")
	private String requestBody;

	@Column(name = "RESPONSE_BODY")
	private String responseBody;

	@Column(name = "SCHOOL_NUMBER")
	private String schoolRegNumber;

	@Column(name = "EMPLOYEE_ID")
	private String employeeId;

}
