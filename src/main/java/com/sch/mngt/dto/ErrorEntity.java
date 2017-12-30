package com.sch.mngt.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ErrorEntity {

	private String httpCode;
	private String developerMessage;
	private String userMessage;
	private List<String> errors;
	
	private StackTraceElement [] stackTraceElement;

}
