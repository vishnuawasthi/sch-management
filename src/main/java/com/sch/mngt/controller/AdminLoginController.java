/**
 * 
 */
package com.sch.mngt.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sch.mngt.dto.ErrorEntity;
import com.sch.mngt.dto.Event;
import com.sch.mngt.dto.LoginDTO;
import com.sch.mngt.dto.LoginResponseDTO;
import com.sch.mngt.dto.LogoutResponseDTO;
import com.sch.mngt.dto.RegistrationResponseDTO;
import com.sch.mngt.dto.Response;
import com.sch.mngt.dto.UserProfileDTO;
import com.sch.mngt.exception.InvalidCredentialException;
import com.sch.mngt.services.CustomUserDetailService;
import com.sch.mngt.services.CustomUserDetails;
import com.sch.mngt.services.LoginService;
import com.sch.mngt.utils.UserValidationUtils;

/**
 * @author Vishnu Awasthi
 *
 */

@RestController
@RequestMapping(value = "/api")
public class AdminLoginController extends BaseController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@PostMapping(value = "/v1/admin/login")
	public HttpEntity<Object> doLogin(@RequestBody @Valid LoginDTO loginDTO, BindingResult result) {
		System.out.println("doLogin() - start");
		Event event = new Event();
		Response response = new Response();
		if (result.hasErrors()) {
			ErrorEntity errorEntity = setValidationError(result);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		try {
			UserDetails userDetails = customUserDetailService.loadUserByUsername(loginDTO.getUsername());

			// Validate user with username and password.
			Authentication authentication = authenticate(userDetails, loginDTO.getPassword());
			// Set user in security context.
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String newApiKey = UserValidationUtils.generateAccessKey();
			LoginResponseDTO accessCredentials = new LoginResponseDTO(authentication.getName(), newApiKey, new Date());
			// Update API key in db
			loginService.updateApiKey(userDetails, newApiKey);
			response.setAccessCredentials(accessCredentials);
			event.setResponse(response);

			System.out.println("doLogin() - end");
			return new ResponseEntity<Object>(event, HttpStatus.OK);
		} catch (InvalidCredentialException e) {
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		} catch (UsernameNotFoundException e) {
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity("This is unexpected case : " + e.getMessage(), "400", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/v1/admin/logout/{username}")
	public HttpEntity<Object> logout(@PathVariable(name = "username", required = true) String username,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		System.out.println("logout() - start");
		Event event = new Event();
		Response response = new Response();
		LogoutResponseDTO logoutRes = new LogoutResponseDTO();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (null != authentication) {
				
				System.out.println("authentication  =>  " + authentication.getName());
				loginService.removeApiKey(authentication.getName());
				new SecurityContextLogoutHandler().logout(httpRequest, httpResponse, authentication);
				logoutRes.setMessage("You have been loggedout successfully.");
			}
			response.setLogoutDetails(logoutRes);
			event.setResponse(response);
		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "code", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		System.out.println("logout() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

	@PostMapping(value = "/v1/admin/user-registration")
	public HttpEntity<Object> register(@RequestBody @Valid UserProfileDTO userProfileDTO, BindingResult result) {
		System.out.println("register() - start");
		Event event = new Event();
		RegistrationResponseDTO regResponse = null;
		Response response = new Response();
		if (result.hasErrors()) {
			ErrorEntity errorEntity = setValidationError(result);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		try {
			regResponse = loginService.save(userProfileDTO);
			response.setRegResponse(regResponse);
			event.setResponse(response);
		} catch (Exception e) {
			ErrorEntity errorEntity = setErrorEntity(e.getMessage(), "code", e);
			return new ResponseEntity<Object>(errorEntity, HttpStatus.BAD_REQUEST);
		}
		System.out.println("register() - end");
		return new ResponseEntity<Object>(event, HttpStatus.OK);
	}

	private Authentication authenticate(UserDetails userDetails, String password) throws InvalidCredentialException {
		if (null != userDetails && password.equals(userDetails.getPassword())) {
			final UserDetails principal = new CustomUserDetails(userDetails.getUsername(), password,
					userDetails.getAuthorities());
			final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password,
					userDetails.getAuthorities());
			return auth;
		} else {
			throw new InvalidCredentialException("Invalid credentials");
		}
	}

}
