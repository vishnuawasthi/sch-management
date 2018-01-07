package com.sch.mngt.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sch.mngt.dto.ErrorEntity;
import com.sch.mngt.dto.Event;
import com.sch.mngt.dto.Response;
import com.sch.mngt.dto.SchoolAdminDTO;
import com.sch.mngt.dto.SchoolDTO;
import com.sch.mngt.exception.RecordNotFoundException;
import com.sch.mngt.services.SchoolAndAdminService;

@RestController
@RequestMapping("/api/admin")
public class SchoolAndAdminController extends BaseController {

	@Autowired
	private SchoolAndAdminService schoolAndAdminService;

	@RequestMapping(value = "/v1/school", method = RequestMethod.POST)
	public HttpEntity<Object> createSchool(@RequestBody @Valid SchoolDTO schoolDTO, BindingResult result) {
		System.out.println("createSchool() - start");
		System.out.println("createSchool  => " + schoolDTO);

		if (result.hasErrors()) {
			if (result.hasErrors()) {
				ErrorEntity errorEntity = setValidationError(result);
				return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
			}
		}

		Event event = new Event();
		Response response = new Response();
		Long id = schoolAndAdminService.createSchool(schoolDTO);
		schoolDTO = new SchoolDTO();
		schoolDTO.setId(id);
		response.setSchoolDetail(schoolDTO);
		event.setResponse(response);
		System.out.println("createSchool() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

	@RequestMapping(value = "/v1/school/{id}", method = RequestMethod.GET)
	public HttpEntity<Object> getSchool(@RequestHeader(name = "schoolId", required = false) String schoolId,
			@PathParam(value = "id") Long id) {
		System.out.println("getSchool() - start");
		System.out.println("id  => " + id);
		Event event = new Event();
		Response response = new Response();
		SchoolDTO schoolDTO = null;
		try {
			schoolDTO = schoolAndAdminService.getSchool(id);
		} catch (RecordNotFoundException e) {
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity("This is unexpected case : " + e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		response.setSchoolDetail(schoolDTO);
		event.setResponse(response);
		System.out.println("getSchool() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

	@RequestMapping(value = "/v1/schools", method = RequestMethod.GET)
	public HttpEntity<Object> getSchools() {
		System.out.println("getSchools() - start");
		Event event = new Event();
		Response response = new Response();
		List<SchoolDTO> schools = null;
		try {
			schools = schoolAndAdminService.getAll();
		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity("This is unexpected case : " + e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		response.setSchoolDetails(schools);
		event.setResponse(response);
		System.out.println("getSchools() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

	@RequestMapping(value = "/v1/school-admin", method = RequestMethod.POST)
	public HttpEntity<Object> createSchoolAdmin(@RequestBody @Valid SchoolAdminDTO schoolAdminDTO,
			BindingResult result) {
		System.out.println("createSchoolAdmin() - start");
		System.out.println("createSchoolAdmin  => " + schoolAdminDTO);

		if (result.hasErrors()) {
			if (result.hasErrors()) {
				ErrorEntity errorEntity = setValidationError(result);
				return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
			}
		}
		Long id = null;
		try {
			id = schoolAndAdminService.createSchoolAdmin(schoolAdminDTO);
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity("This is unexpected case : " + e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		Event event = new Event();
		Response response = new Response();
		schoolAdminDTO.setId(id);
		response.setSchoolAdminDetail(schoolAdminDTO);
		event.setResponse(response);
		System.out.println("createSchoolAdmin() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

}
