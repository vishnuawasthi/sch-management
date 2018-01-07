package com.sch.mngt.dto;

import java.util.HashSet;
import java.util.Set;

import com.sch.mngt.entity.Class;

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
public class FacultyDetailsDTO {

	private Long id;

	private String facultyName;

	private String email;

	private String contactNumer;
	
	private Set<Class> classes ;

}
