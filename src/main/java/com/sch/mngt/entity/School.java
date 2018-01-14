package com.sch.mngt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "SCHOOL_DETAIL")
@SequenceGenerator(name = "SEQ_SCHOOL_DETAIL", sequenceName = "SEQ_SCHOOL_DETAIL", initialValue = 1)
public class School extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SCHOOL_DETAIL")
	@Column(name = "ID")
	private Long id;

	@Column(name = "SCHOOL_NAME")
	private String schoolName;

	@Column(name = "REG_NUMBER")
	private String registrationNumber;

	@OneToMany(mappedBy = "school",fetch=FetchType.LAZY)
	private Set<User> users = new HashSet<User>();

	@OneToMany(mappedBy = "school",fetch=FetchType.LAZY)
	private Set<com.sch.mngt.entity.Class> classes = new HashSet<com.sch.mngt.entity.Class>();

	@OneToMany(mappedBy = "school",fetch=FetchType.LAZY)
	private Set<Faculty> faculties = new HashSet<Faculty>();

	@OneToMany(mappedBy = "school",fetch=FetchType.LAZY)
	private Set<Student> students = new HashSet<Student>();
	
	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
	private Set<UserRole> roles = new HashSet<UserRole>();

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

}
