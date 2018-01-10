package com.sch.mngt.entity;

import java.util.HashSet;
import java.util.Set;

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

	@Column(name = "ADDRESS")
	private String adddress;
	
	/*@OneToMany(mappedBy="school")
	private Set<SchoolAdmin> admins = new HashSet<SchoolAdmin>();
*/	
	@OneToMany(mappedBy="school")
	private Set<User> users = new HashSet<User>();
	
	@OneToMany(mappedBy="school")
	private Set<Class> classes = new HashSet<Class>();
	
	@OneToMany(mappedBy="school")
	private Set<Faculty> faculties = new HashSet<Faculty>();
	
	

}
