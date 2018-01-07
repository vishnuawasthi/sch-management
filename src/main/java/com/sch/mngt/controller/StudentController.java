package com.sch.mngt.controller;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sch.mngt.dto.AttendanceDTO;
import com.sch.mngt.dto.ErrorEntity;
import com.sch.mngt.dto.Event;
import com.sch.mngt.dto.RegistrationResponseDTO;
import com.sch.mngt.dto.Response;

@RestController
@RequestMapping(value = "/api")
public class StudentController extends BaseController {

	// AttendanceDTO

	@PostMapping(value = "/v1/attendace")
	public HttpEntity<Object> attendace(
			@RequestBody @Valid AttendanceDTO attendanceDTO, 
			BindingResult result) {
		System.out.println("attendace() - start");
		Event event = new Event();
		if (result.hasErrors()) {
			ErrorEntity errorEntity = setValidationError(result);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		try {

		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "code", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		System.out.println("attendace() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}
	
	
	
	
}
