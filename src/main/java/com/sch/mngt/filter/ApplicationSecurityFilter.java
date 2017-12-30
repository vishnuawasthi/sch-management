package com.sch.mngt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sch.mngt.dto.ErrorEntity;
import com.sch.mngt.services.CustomUserDetailService;

public class ApplicationSecurityFilter extends DelegatingFilterProxy {

	private CustomUserDetailService customUserDetailService;

	public ApplicationSecurityFilter(CustomUserDetailService customUserDetailService) {
		this.customUserDetailService = customUserDetailService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String[] urlPaths = httpServletRequest.getRequestURI().split("/");
		
		String apiKey = httpServletRequest.getHeader("APIKEY");
		
		if ("api".equalsIgnoreCase(urlPaths[2])) {
			if ("secrete123".equals(apiKey)) {
				filterChain.doFilter(httpServletRequest, httpServletResponse);
			} else {
				prepareUnauthorizedAccessResponse(httpServletResponse);
			}
		} else {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	private void prepareUnauthorizedAccessResponse(HttpServletResponse response) {
		ErrorEntity entity = new ErrorEntity();
		String errorString = "";
		ObjectMapper objectMapper = new ObjectMapper();

		entity.setDeveloperMessage("Bad Request : Preliminary validation failed. Please provide valid access headers.");
		entity.setHttpCode("400");
		entity.setUserMessage("Bad Request : Preliminary validation failed. Please provide valid access headers.");
		try {

			errorString = objectMapper.writeValueAsString(entity);
			response.setStatus(400);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().write(errorString);
			response.flushBuffer();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
