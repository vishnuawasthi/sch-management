package com.sch.mngt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassDetailsDTO {

	private Long id;
	private String grade;
	private String description;
	private Long schoolId;

}
