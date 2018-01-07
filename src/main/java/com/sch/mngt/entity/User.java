package com.sch.mngt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_DETAILS")
@SequenceGenerator(name = "SEQ_USER_DETAILS", sequenceName = "SEQ_USER_DETAILS", initialValue = 1)
public class User {

	@Id
	@GeneratedValue(generator = "SEQ_USER_DETAILS", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_NAME", unique = true, nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "MIDDE_NAME")
	private String middleName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	@Column(name = "SCHOOL_NUMBER")
	private String schoolRegNumber;

	@Column(name = "EMPLOYEE_ID")
	private String employeeId;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserRole> roles = new HashSet<UserRole>();

}
