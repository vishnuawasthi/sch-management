package com.sch.mngt.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sch.mngt.dto.AccessPermissionsDTO;
import com.sch.mngt.dto.ClassDetailsDTO;
import com.sch.mngt.dto.ErrorEntity;
import com.sch.mngt.dto.Event;
import com.sch.mngt.dto.Response;
import com.sch.mngt.dto.SchoolAdminDTO;
import com.sch.mngt.dto.SchoolDTO;
import com.sch.mngt.dto.UserRoleDTO;
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
	public HttpEntity<Object> getSchool(@PathParam(value = "id") Long id) {
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

	/*
	 * @RequestMapping(value = "/v1/school-admin", method = RequestMethod.POST)
	 * public HttpEntity<Object> createSchoolAdmin(@RequestBody @Valid
	 * SchoolAdminDTO schoolAdminDTO, BindingResult result) {
	 * System.out.println("createSchoolAdmin() - start"); System.out.println(
	 * "createSchoolAdmin  => " + schoolAdminDTO);
	 * 
	 * if (result.hasErrors()) { if (result.hasErrors()) { ErrorEntity
	 * errorEntity = setValidationError(result); return new
	 * ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST); } } Long id
	 * = null; try { id =
	 * schoolAndAdminService.createSchoolAdmin(schoolAdminDTO); } catch
	 * (RecordNotFoundException e) { e.printStackTrace(); ErrorEntity
	 * errorEntity = setErrorEntity(e.getMessage(), "400", e); return new
	 * ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST); } catch
	 * (Exception e) { ErrorEntity errorEntity = setErrorEntity(
	 * "This is unexpected case : " + e.getMessage(), "400", e); return new
	 * ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST); } Event
	 * event = new Event(); Response response = new Response();
	 * schoolAdminDTO.setId(id); response.setSchoolAdminDetail(schoolAdminDTO);
	 * event.setResponse(response); System.out.println(
	 * "createSchoolAdmin() - end"); return new ResponseEntity<Object>(event,
	 * HttpStatus.OK); }
	 */
	@RequestMapping(value = "/v1/class-detail", method = RequestMethod.POST)
	public HttpEntity<Object> createClass(@RequestBody @Valid ClassDetailsDTO classDetailsDTO, BindingResult result) {
		System.out.println("createClass() - start");
		System.out.println("classDetailsDTO  => " + classDetailsDTO);

		if (result.hasErrors()) {
			if (result.hasErrors()) {
				ErrorEntity errorEntity = setValidationError(result);
				return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
			}
		}
		Long id = null;
		try {
			id = schoolAndAdminService.createClass(classDetailsDTO);
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
		classDetailsDTO.setId(id);
		response.setClassDetail(classDetailsDTO);
		event.setResponse(response);
		System.out.println("createClass() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

	@RequestMapping(value = "/v1/access-permissions", method = RequestMethod.POST)
	public HttpEntity<Object> accessPermissions(@RequestBody @Valid AccessPermissionsDTO accessPermissionsDTO,
			BindingResult result) {
		System.out.println("accessPermissions() - start");
		System.out.println("accessPermissions  => " + accessPermissionsDTO);

		if (result.hasErrors()) {
			if (result.hasErrors()) {
				ErrorEntity errorEntity = setValidationError(result);
				return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
			}
		}
		Event event = new Event();
		Response response = new Response();
		try {
			schoolAndAdminService.updateAccessFunction(accessPermissionsDTO);
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		event.setResponse(response);
		System.out.println("accessPermissions() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

	@RequestMapping(value = "/v1/user-details/{id}", method = RequestMethod.GET)
	public HttpEntity<Object> getUserDetails(@PathVariable(value = "id", required = true) Long id) {
		System.out.println("getUserDetails() - start");
		Event event = new Event();
		Response response = new Response();
		SchoolAdminDTO schoolAdminDTO = null;
		try {
			schoolAdminDTO = schoolAndAdminService.getAdminDetails(id);

		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity("This is unexpected case : " + e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		response.setSchoolAdminDetail(schoolAdminDTO);
		event.setResponse(response);
		System.out.println("getUserDetails() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

	@RequestMapping(value = "/v1/user-role", method = RequestMethod.POST)
	public HttpEntity<Object> createRole(@RequestBody UserRoleDTO userRoleDTO, BindingResult result) {
		System.out.println("createRole() - start");
		if (result.hasErrors()) {
			if (result.hasErrors()) {
				ErrorEntity errorEntity = setValidationError(result);
				return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
			}
		}
		Event event = new Event();
		Response response = new Response();
		try {
			Long id = schoolAndAdminService.createRole(userRoleDTO);
			userRoleDTO.setId(id);
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity("This is unexpected case : " + e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		response.setUserRole(userRoleDTO);
		event.setResponse(response);

		System.out.println("createRole() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

}
