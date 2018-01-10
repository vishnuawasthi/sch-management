package com.sch.mngt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sch.mngt.constant.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_ROLE")
@SequenceGenerator(name = "SEQ_USER_ROLE", sequenceName = "SEQ_USER_ROLE", initialValue = 1)
public class UserRole {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ROLE")
	private Long id;

	@Column(name = "ROLE_NAME", unique = true, nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType roleName;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	

}
