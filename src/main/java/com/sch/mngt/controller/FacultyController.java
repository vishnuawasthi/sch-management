package com.sch.mngt.controller;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sch.mngt.dto.Event;
import com.sch.mngt.dto.FacultyDetailsDTO;
import com.sch.mngt.dto.Response;

@RestController
@RequestMapping(value = "/api")
public class FacultyController extends BaseController {

	@RequestMapping(value = "/v1/faculty", method = RequestMethod.POST)
	public HttpEntity<Object> saveFaculty(@RequestBody @Valid FacultyDetailsDTO facultyDetailsDTO,
			BindingResult result) {
		System.out.println("saveFaculty() - start");
		System.out.println("faculty  => " + facultyDetailsDTO);
		Event event = new Event();
		Response response = new Response();
		event.setResponse(response);
		System.out.println("saveFaculty() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}
}
