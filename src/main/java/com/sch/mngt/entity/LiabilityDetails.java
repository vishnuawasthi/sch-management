/*package com.sch.mngt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LIABILITY_DETAILS")
@SequenceGenerator(name = "SEQ_LIABILITY_DETAILS", sequenceName = "SEQ_LIABILITY_DETAILS", initialValue = 1)
public class LiabilityDetails {

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user") )
	@Id
	@Column(name = "ID")
	private Long id;

	@OneToMany(mappedBy = "liabilityDetails")
	private Set<Class> assignedClasses = new HashSet<Class>();

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private User user;

}
*/