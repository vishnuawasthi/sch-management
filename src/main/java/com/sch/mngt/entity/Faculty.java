package com.sch.mngt.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "FACULTY_DETAILS")
@SequenceGenerator(name = "SEQ_FACULTY_DETAILS", sequenceName = "SEQ_FACULTY_DETAILS", initialValue = 1)
// Faculty and Staff
public class Faculty implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8607598143053319683L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FACULTY_DETAILS")
	@Column(name = "ID")
	private Long id;

	@Column(name = "FACULTY_NAME")
	private String facultyName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "CONTACT_NUMBER")
	private String contactNumer;
	
	@ManyToOne
	@JoinColumn(name = "SCHOOL_SEQNUM")
	private School school;

	@ManyToMany(mappedBy = "faculties")
	private Set<com.sch.mngt.entity.Class> classes = new HashSet<com.sch.mngt.entity.Class>();

}
