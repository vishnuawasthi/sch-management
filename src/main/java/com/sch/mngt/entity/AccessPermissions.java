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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
// @ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCESS_PERMISSION")
@SequenceGenerator(name = "SEQ_ACCESS_PERMISSION", sequenceName = "SEQ_ACCESS_PERMISSION", initialValue = 1)
public class AccessPermissions {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCESS_PERMISSION")
	@Id
	@Column(name = "ID")
	private Long id;

	@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_DETAILS_SEQNUM", referencedColumnName = "ID")
	private User user;

	@OneToMany(mappedBy = "accessPermissions",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<com.sch.mngt.entity.Class> classes = new HashSet<com.sch.mngt.entity.Class>();

}
