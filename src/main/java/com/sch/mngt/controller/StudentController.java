package com.sch.mngt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sch.mngt.dto.AttendanceDTO;
import com.sch.mngt.dto.ErrorEntity;
import com.sch.mngt.dto.Event;
import com.sch.mngt.dto.Response;
import com.sch.mngt.dto.StudentDetailsDTO;
import com.sch.mngt.exception.RecordNotFoundException;
import com.sch.mngt.services.StudentService;

@RestController
@RequestMapping(value = "/api")
public class StudentController extends BaseController {

	@Autowired
	private StudentService studentService;

	@PostMapping(value = "/v1/student")
	public HttpEntity<Object> createStudent(@RequestBody @Valid StudentDetailsDTO studentDetail, BindingResult result) {
		System.out.println("createStudent() - start");
		Event event = new Event();
		if (result.hasErrors()) {
			ErrorEntity errorEntity = setValidationError(result);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		Response response = new Response();
		try {
			Long id = studentService.createStudentProfile(studentDetail);
			studentDetail.setId(id);
			response.setStudentDetail(studentDetail);
			event.setResponse(response);
		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "code", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		System.out.println("createStudent() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

	@PutMapping(value = "/v1/student")
	public HttpEntity<Object> updateStudent(@RequestBody @Valid StudentDetailsDTO studentDetail, BindingResult result) {
		System.out.println("updateStudent() - start");
		Event event = new Event();
		Response response = new Response();

		if (result.hasErrors()) {
			ErrorEntity errorEntity = setValidationError(result);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}

		try {

			StudentDetailsDTO updatedDetails = studentService.updateStudentProfile(studentDetail);
			response.setStudentDetail(updatedDetails);
			event.setResponse(response);

		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "code", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		System.out.println("updateStudent() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

	@GetMapping(value = "/v1/student/{usernameOrId}")
	public HttpEntity<Object> loadStudentByUsernameOrId(
			@PathVariable(name = "usernameOrId", required = true) String usernameOrId,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		System.out.println("loadStudentByUsernameOrId() - start");
		Event event = new Event();
		Response response = new Response();
		try {
			StudentDetailsDTO studentDetail = null;
			boolean isNumeric = 	StringUtils.isNumeric(usernameOrId);
			if(isNumeric){
				studentDetail = studentService.loadStudentById(Long.parseLong(usernameOrId));
			}else{
				studentDetail = studentService.loadStudentByUsername(usernameOrId);
			}
			response.setStudentDetail(studentDetail);
			event.setResponse(response);
		} catch (RecordNotFoundException e) {
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "code", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "code", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		System.out.println("loadStudentByUsernameOrId() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

	@PostMapping(value = "/v1/attendace")
	public HttpEntity<Object> attendace(@RequestBody @Valid AttendanceDTO attendanceDTO, BindingResult result) {
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
