package com.sch.mngt.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
//@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS")
@SequenceGenerator(name = "SEQ_ADDRESS", sequenceName = "SEQ_ADDRESS", initialValue = 1)
public class Address {

	@Id
	// @GenericGenerator(name = "generator", strategy = "foreign", parameters =
	// @Parameter(name = "property", value = "school") )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
	@Column(name = "ID")
	private Long id;

	@Column(name = "ADDRESS_LINE1")
	private String addressLine1;

	@Column(name = "ADDRESS_LINE2")
	private String addressLine2;

	@Column(name = "ZIPCODE")
	private String zipcode;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "COUNTRY")
	private String country;

	// @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	
	@OneToOne(targetEntity = School.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "SCHOOL_SEQNUM", referencedColumnName = "ID")
	private School school;

}
