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

import com.sch.mngt.constant.AttendanceFlagType;
import com.sch.mngt.constant.MonthsName;

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
@Table(name = "MONTHLY_ATTENDANCE")
@SequenceGenerator(name = "SEQ_MONTHLY_ATTENDANCE")
public class MonthlyAttendance {

	// @GenericGenerator(name = "generator", strategy = "foreign", parameters =
	// @Parameter(name = "property", value = "monthDetails") )
	
	@Id
	@GeneratedValue(generator = "SEQ_MONTHLY_ATTENDANCE",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "MONTH_NAME")
	@Enumerated(EnumType.STRING)
	private MonthsName monthName;
	
	@Enumerated
	private AttendanceFlagType d1;
	@Enumerated
	private AttendanceFlagType d2;
	@Enumerated
	private AttendanceFlagType d3;
	@Enumerated
	private AttendanceFlagType d4;
	@Enumerated
	private AttendanceFlagType d5;
	@Enumerated
	private AttendanceFlagType d6;
	@Enumerated
	private AttendanceFlagType d7;
	@Enumerated
	private AttendanceFlagType d8;
	@Enumerated
	private AttendanceFlagType d9;
	@Enumerated
	private AttendanceFlagType d10;
	@Enumerated
	private AttendanceFlagType d11;
	@Enumerated
	private AttendanceFlagType d12;
	@Enumerated
	private AttendanceFlagType d13;
	@Enumerated
	private AttendanceFlagType d14;
	@Enumerated
	private AttendanceFlagType d15;
	@Enumerated
	private AttendanceFlagType d16;
	@Enumerated
	private AttendanceFlagType d17;
	@Enumerated
	private AttendanceFlagType d18;
	@Enumerated
	private AttendanceFlagType d19;
	@Enumerated
	private AttendanceFlagType d20;
	@Enumerated
	private AttendanceFlagType d21;
	@Enumerated
	private AttendanceFlagType d22;
	@Enumerated
	private AttendanceFlagType d23;
	@Enumerated
	private AttendanceFlagType d24;
	@Enumerated
	private AttendanceFlagType d25;
	@Enumerated
	private AttendanceFlagType d26;
	@Enumerated
	private AttendanceFlagType d27;
	@Enumerated
	private AttendanceFlagType d28;
	@Enumerated
	private AttendanceFlagType d29;
	@Enumerated
	private AttendanceFlagType d30;

	@Enumerated
	private AttendanceFlagType d31;
	
	@ManyToOne
	@JoinColumn(name="STUDENT_DETAILS_SEQNUM")
	private Student student;

	/*
	 * @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) private
	 * MonthDetails monthDetails;
	 */
}