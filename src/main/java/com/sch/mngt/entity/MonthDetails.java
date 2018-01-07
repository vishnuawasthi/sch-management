/*package com.sch.mngt.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Table(name = "ATTENTANCE_SHEET")
@SequenceGenerator(name = "SEQ_ATTENTANCE_SHEET", sequenceName = "SEQ_ATTENTANCE_SHEET", initialValue = 1)
public class MonthDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "MONTH_NAME", unique = true)
	@Enumerated(EnumType.STRING)
	private MonthsName monthName;

	@OneToOne(mappedBy = "monthDetails")
	@PrimaryKeyJoinColumn
	private MonthAttendance monthAttendance;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ATTENDANCE_SHEET_SEQNUM")
	private AttendanceSheet attendanceSheet;

}
*/